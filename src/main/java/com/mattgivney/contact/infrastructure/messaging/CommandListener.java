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
package com.mattgivney.contact.infrastructure.messaging;

import com.mattgivney.contact.domain.cmd.Command;
import com.mattgivney.contact.domain.cmd.CommandHandlerException;
import com.mattgivney.contact.domain.cmd.ContactCommandHandlers;
import java.lang.reflect.Method;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Processes all asynchronous state change requests
 * 
 * @author matt
 */
public class CommandListener implements MessageListener{

    private ContactCommandHandlers commandHandlers;

    @Override
    public void onMessage(final Message msg) {
        try{
            if(msg instanceof ObjectMessage){
                    ObjectMessage om = (ObjectMessage)msg;
                    Command cmd = (Command)om.getObject();
                    handle(cmd);
            }
        }catch(JMSException e){
            throw new CommandHandlerException(e);
        }
    }

    public void setContactCommandHandlers(final ContactCommandHandlers handlers){
        this.commandHandlers = handlers;
    }

    public void handle(final Command command){
        //FIXME there's got to be a better way to do this
        //other than using the Reflection API (that is
        //other than creating a listener for each type
        //of command).
        try{
            Method m = commandHandlers.getClass()
                              .getDeclaredMethod("handle",
                                    command.getClass());
            m.invoke(commandHandlers,command);
        }catch(Exception e){
            throw new CommandHandlerException("Failed to handle command!", e);
        }

    }
}
