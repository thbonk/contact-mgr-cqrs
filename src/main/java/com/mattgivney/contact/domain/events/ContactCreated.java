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
import java.util.UUID;

/**
 *
 * @author matt
 */
public class ContactCreated implements Event{
    
    private UUID identity;
    private String firstName;
    private String lastName;

    public ContactCreated(UUID identity, String firstName, String lastName) {
        this.identity = identity;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public UUID getIdentity() {
        return identity;
    }

    public String getLastName() {
        return lastName;
    }


}
