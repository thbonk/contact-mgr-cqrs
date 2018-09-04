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
package com.mattgivney.contact.infrastructure.persistence;

import com.mattgivney.contact.domain.Contact;
import com.mattgivney.contact.domain.ContactRepository;
import com.mattgivney.contact.domain.Event;
import com.mattgivney.contact.domain.events.EventStore;
import java.util.List;
import java.util.UUID;

/**
 * A simple implementation of the ContactRepository
 * 
 * @author matt
 */
public class PersistentContacts implements ContactRepository{

    private EventStore eventStore;

    public PersistentContacts(final EventStore eventStore){
        this.eventStore = eventStore;
    }

    @Override
    public void store(final Contact contact) {
        final List<Event> events = contact.getUncommittedChanges();
        eventStore.storeEvents(contact.identity(), events, contact.version());
        contact.markChangesCommitted();
    }

    @Override
    public Contact load(UUID aggregateId) {
        int version = eventStore.getCurrentVersion(aggregateId);
        final Contact contact = new Contact();
        contact.loadFromHistory(
                eventStore.sourceEvents(aggregateId), version);
        return contact;
    }

    //For Spring
    protected PersistentContacts(){}
}
