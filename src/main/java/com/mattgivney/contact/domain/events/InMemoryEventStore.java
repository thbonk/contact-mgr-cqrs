/*
 * Copyright 2010 Matt Givney
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.mattgivney.contact.domain.events;

import com.mattgivney.contact.domain.Event;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Defines a simple in memory event store
 * @author matt
 */
public class InMemoryEventStore implements EventStore{

    private Map<UUID,List<StoredEvent>> storedEvents =
                new LinkedHashMap<UUID,List<StoredEvent>>();

    /**
     * Stores events into an In-memory cache
     *
     * @param aggregateId the unique id
     * @param events the events to store
     * @param expectedVersion the version of the aggregate prior to committing
     *          these new events.
     */
    @Override
    public void storeEvents(UUID aggregateId, List<Event> events, int expectedVersion) {
        List<StoredEvent> committedEvents;
        if(storedEvents.containsKey(aggregateId)){
            committedEvents = storedEvents.get(aggregateId);
        }else{
            committedEvents = new LinkedList<StoredEvent>();
        }
        //a simple optimistic locking check
        checkForStaleData(aggregateId, expectedVersion);

        //load in the new events
        for(Event event : events){
           expectedVersion++;
           committedEvents.add(new StoredEvent(event, expectedVersion));
        }
        storedEvents.put(aggregateId,committedEvents);
    }

    /**
     * Does a quick optimistic locking check
     *
     * @param aggregateId the UUID
     * @param expectedVersion the version of the aggregate prior to committing
     *          these new events.
     * @throws StaleDataException if the versions don't match
     */
    protected void checkForStaleData(UUID aggregateId, int expectedVersion){
        int lastVersion = getCurrentVersion(aggregateId);
        if(expectedVersion!=lastVersion){
            throw new StaleDataException("Expected version was " + expectedVersion + 
                    " but last committed version was " + lastVersion);
        }
    }

    /**
     * Gets the version of the last event
     * 
     * @param aggregateId the unique id for the aggregate
     * @return the last version number or 0 if no events exist for this UUID
     */
    @Override
    public int getCurrentVersion(UUID aggregateId){
        List<StoredEvent> stored = storedEvents.get(aggregateId);
        if(stored==null){
            return 0;
        }else{
            StoredEvent last = stored.get(stored.size()-1);
            return last.getVersion();
        }
    }

    /**
     * Loads all the events for a given aggregate id so they
     * can be replayed to recreate the object in it's current state
     * 
     * @param aggregateId the UUID
     * @return List<Event> to replay against the aggregate
     */
    @Override
    public List<Event> sourceEvents(UUID aggregateId) {
        final List<Event> events = new LinkedList<Event>();
        if(storedEvents.containsKey(aggregateId)){
            final List<StoredEvent> cachedEvents =
                    storedEvents.get(aggregateId);
            for(StoredEvent e : cachedEvents){
                events.add(e.getEvent());
            }
        }
        return events;
    }

    /**
     * The cached data type
     */
    protected class StoredEvent{
        private Event event;
        private int version;
        protected StoredEvent(Event e, int version){
            this.event = e;
            this.version = version;
        }
        public Event getEvent(){ return this.event;}
        public int getVersion(){ return this.version;}
    }
}
