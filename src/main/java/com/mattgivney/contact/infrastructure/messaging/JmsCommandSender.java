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
import com.mattgivney.contact.domain.cmd.CommandSender;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Publishes commands to any configured queue.
 *
 * @author matt
 */
public class JmsCommandSender implements CommandSender{

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setConnectionFactory(final ConnectionFactory factory){
        this.jmsTemplate = new JmsTemplate(factory);
    }

    public void setQueue(final Queue queue){
        this.queue = queue;
    }

    @Override
    public void send(final Command command) {
        this.jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(final Session session)
                    throws JMSException {
                    return session.createObjectMessage(command);
            }
        });
    }

}
