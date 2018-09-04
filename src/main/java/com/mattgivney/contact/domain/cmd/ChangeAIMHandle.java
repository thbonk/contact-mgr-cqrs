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
 * Represents the change AIM handle state change
 * 
 * @author matt
 */
public class ChangeAIMHandle extends ContactCommand{

    private String aimHandle;

    public ChangeAIMHandle(final UUID id, String handle){
        super(id);
        Validate.notEmpty(handle, "The handle cannot be null or empty!");
        this.aimHandle = handle;
    }

    public String getAIMHandle(){
        return this.aimHandle;
    }
}
