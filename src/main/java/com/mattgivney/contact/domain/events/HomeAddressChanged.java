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

import java.util.UUID;
import com.mattgivney.contact.domain.Address;
import com.mattgivney.contact.domain.Event;

/**
 *
 * @author matt
 */
public class HomeAddressChanged implements Event{
    
    private UUID identity;
    private Address homeAddress;

    public HomeAddressChanged(UUID identity, Address homeAddress) {
        this.identity = identity;
        this.homeAddress = homeAddress;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public UUID getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return "HomeAddressChanged{" + "identity=" + identity +
                "homeAddress=" + homeAddress + '}';
    }

    

}
