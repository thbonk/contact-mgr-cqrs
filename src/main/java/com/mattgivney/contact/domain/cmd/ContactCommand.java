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

import java.util.UUID;
import org.apache.commons.lang.Validate;

/**
 *
 * @author matt
 */
public class ContactCommand implements Command{

    private UUID contactId;
    
    public ContactCommand(UUID contactId){
        Validate.notNull(contactId, "Contact Id is required");
        this.contactId = contactId;
    }

    public UUID getContactId(){
        return this.contactId;
    }
    
}
