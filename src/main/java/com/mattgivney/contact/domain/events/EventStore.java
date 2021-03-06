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
import java.util.List;
import java.util.UUID;

/**
 *
 * @author matt
 */
public interface EventStore {
    public void storeEvents(UUID aggregateId, List<Event> events, int expectedVersion);
    public List<Event> sourceEvents(UUID aggregateId);
    public int getCurrentVersion(UUID aggregateId);
}
