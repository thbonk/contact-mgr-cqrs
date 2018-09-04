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
package com.mattgivney.contact.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author matt
 */
public abstract class AggregateRoot implements Serializable{

    protected UUID identity;
    protected int version;
    private List<Event> uncommittedChanges = new LinkedList<Event>();

    public UUID identity(){
        return this.identity;
    }

    public int version(){
        return this.version;
    }

    public List<Event> getUncommittedChanges(){
        return this.uncommittedChanges;
    }

    public void markChangesCommitted(){
        this.uncommittedChanges.clear();
    }


    protected void applyEvent(Event event){
        this.uncommittedChanges.add(event);
    }

    public void loadFromHistory(List<Event> eventsFromHistory, int currentVersion){
        for(Event event : eventsFromHistory ){
            replay(event);
        }
        this.markChangesCommitted();
        this.version = currentVersion;
    }

    protected abstract void replay(Event event);

}
