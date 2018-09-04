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

import com.mattgivney.contact.domain.Contact;
import com.mattgivney.contact.domain.ContactRepository;

/**
 * Handlers for the various contact state changes.
 *
 * @author matt
 */
public class ContactCommandHandlers {

    private ContactRepository repository;

    public void handle(final ChangeHomeAddress command){
        final Contact contact = repository.load(command.getContactId());
        contact.changeHomeAddress(command.getAddress());
        repository.store(contact);
    }

    public void handle(final ChangePhoneNumbers command){
        final Contact contact = repository.load(command.getContactId());
        contact.changePhoneNumbers(command.getPhoneNumbers());
        repository.store(contact);
    }

    public void handle(final ChangeWorkAddress command){
        final Contact contact = repository.load(command.getContactId());
        contact.changeWorkAddress(command.getAddress());
        repository.store(contact);
    }

    public void handle(final CreateContact command){
        final Contact contact = new Contact(
                command.getFirstName(), command.getLastName());
        repository.store(contact);
    }

    public void handle(final RemoveHomeAddress command){
        final Contact contact = repository.load(command.getContactId());
        contact.removeHomeAddress();
        repository.store(contact);
    }

    public void handle(final RemoveWorkAddress command){
        final Contact contact = repository.load(command.getContactId());
        contact.removeWorkAddress();
        repository.store(contact);
    }

    public void handle(final ChangeEmailAddresses command){
        final Contact contact = repository.load(command.getContactId());
        contact.changeEmailAddresses(command.getEmailAddresses());
        repository.store(contact);
    }

    public void handle(final ChangeAIMHandle command){
        final Contact contact = repository.load(command.getContactId());
        contact.changeAIMHandle(command.getAIMHandle());
        repository.store(contact);
    }

    public void handle(final RemoveAIMHandle command){
        final Contact contact = repository.load(command.getContactId());
        contact.removeAIMHandle();
        repository.store(contact);
    }
}
