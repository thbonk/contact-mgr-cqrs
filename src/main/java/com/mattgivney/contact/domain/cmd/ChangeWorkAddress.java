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
package com.mattgivney.contact.domain.cmd;

import com.mattgivney.contact.domain.Address;
import java.util.UUID;
import org.apache.commons.lang.Validate;

/**
 *
 * @author matt
 */
public class ChangeWorkAddress extends ContactCommand{

    private Address address;

    private ChangeWorkAddress(final UUID id,
                             final String street,
                             final String suite,
                             final String city,
                             final String state,
                             final String postalCode){
        super(id);
        Validate.notNull(street, "Street is required");
        Validate.notNull(city,"City is required");
        Validate.notEmpty(state,"State is required");
        address = new Address(street, suite, city, state, postalCode);
    }

    public Address getAddress(){
        return this.address;
    }
    
}
