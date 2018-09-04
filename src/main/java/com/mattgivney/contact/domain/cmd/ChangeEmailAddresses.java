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

import com.mattgivney.contact.domain.EmailAddresses;
import java.util.UUID;

/**
 * Represents the change email addresses state change
 * 
 * @author matt
 */
public class ChangeEmailAddresses extends ContactCommand{

    private EmailAddresses emailAddresses;

    public ChangeEmailAddresses(final UUID id,
                                final String homeEmail,
                                final String workEmail,
                                final String alternateEmail){
        super(id);
        if(homeEmail==null && workEmail==null && alternateEmail==null){
            throw new IllegalArgumentException("At least one email address must be valid!");
        }
        emailAddresses = new EmailAddresses(workEmail, homeEmail, alternateEmail);
    }

    public EmailAddresses getEmailAddresses(){
        return this.emailAddresses;
    }
}
