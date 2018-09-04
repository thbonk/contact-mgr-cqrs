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

import com.mattgivney.contact.domain.PhoneNumbers;
import java.util.UUID;

/**
 * Represents the change phone number state change
 * 
 * @author matt
 */
public class ChangePhoneNumbers extends ContactCommand{

    private PhoneNumbers phoneNumbers;

    public ChangePhoneNumbers(final UUID id,
                              final String homePhoneNumber,
                              final String workPhoneNumber,
                              final String mobilePhoneNumber,
                              final String alternatePhoneNumber){
        super(id);
        if(homePhoneNumber==null && workPhoneNumber==null &&
           mobilePhoneNumber==null && alternatePhoneNumber==null){
            throw new IllegalArgumentException("At least one phone number must be valid");
        }

        phoneNumbers = new PhoneNumbers(homePhoneNumber, workPhoneNumber,
                                        mobilePhoneNumber, alternatePhoneNumber);
    }

    public PhoneNumbers getPhoneNumbers(){
        return this.phoneNumbers;
    }
}
