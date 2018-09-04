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
package com.mattgivney.contact.infrastructure.persistence;

import org.junit.Before;
import com.mattgivney.contact.domain.events.ContactCreated;
import com.mattgivney.contact.domain.Event;
import java.util.List;
import com.mattgivney.contact.domain.EmailAddresses;
import java.util.UUID;
import com.mattgivney.contact.domain.Contact;
import com.mattgivney.contact.domain.PhoneNumbers;
import com.mattgivney.contact.domain.events.AIMHandleChanged;
import com.mattgivney.contact.domain.events.EmailAddressesChanged;
import com.mattgivney.contact.domain.events.EventStore;
import com.mattgivney.contact.domain.events.InMemoryEventStore;
import com.mattgivney.contact.domain.events.PhoneNumbersChanged;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author matt
 */
public class PersistentContactsTests {

    private PersistentContacts repository;
    private EventStore eventStore =
            new InMemoryEventStore();
    private PhoneNumbers phoneNumbers;
    private EmailAddresses emailAddresses;

    private UUID aggregateId = UUID.randomUUID();

    @Before
    public void beforeEachTest(){
        repository = new PersistentContacts(eventStore);
        phoneNumbers =
                new PhoneNumbers("555-555-1111","555-555-2222",
                                 "555-555-3333","555-555-4444");
        emailAddresses =
                new EmailAddresses(
                    "work@mail.com","home@mail.com","alternate@mail.com");

    }

    protected Contact contact(){
        final Contact c = new Contact(aggregateId,"Matt","Givney");
        c.changeAIMHandle("captaincrunch");
        c.changePhoneNumbers(phoneNumbers);
        c.changeEmailAddresses(emailAddresses);
        assertNotNull(repository);
        return c;
    }

    @Test
    public void testStoreEvents(){
        Contact c = contact();
        repository.store(c);
        //now lets confirm the all the appropriate events are stored
        List<Event> events = eventStore.sourceEvents(aggregateId);
        assertEquals(4, events.size());
        assertTrue(events.get(0) instanceof ContactCreated);
        assertTrue(events.get(1) instanceof AIMHandleChanged);
        assertTrue(events.get(2) instanceof PhoneNumbersChanged);
        assertTrue(events.get(3) instanceof EmailAddressesChanged);
    }

    @Test
    public void testLoadContactByEvents(){
        Contact c = contact();
        repository.store(c);
        final Contact contact = repository.load(aggregateId);
        assertEquals(contact.getFirstName(), "Matt");
        assertEquals(contact.getLastName(), "Givney");
        assertEquals(contact.getEmailAddress(),emailAddresses);
        assertEquals(contact.getPhoneNumbers(), phoneNumbers);
        
    }
}
