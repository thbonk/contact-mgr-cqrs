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
package com.mattgivney.contact.domain;

import com.mattgivney.contact.domain.events.AIMHandleChanged;
import com.mattgivney.contact.domain.events.AIMHandleRemoved;
import com.mattgivney.contact.domain.events.ContactCreated;
import com.mattgivney.contact.domain.events.EmailAddressesChanged;
import com.mattgivney.contact.domain.events.HomeAddressChanged;
import com.mattgivney.contact.domain.events.HomeAddressRemoved;
import com.mattgivney.contact.domain.events.PhoneNumbersChanged;
import com.mattgivney.contact.domain.events.WorkAddressChanged;
import com.mattgivney.contact.domain.events.WorkAddressRemoved;
import java.lang.reflect.Method;
import java.util.UUID;

import org.apache.commons.lang.Validate;

/**
 * Represents a contact in the contact manager
 *
 * <p>Also defines an aggregate root as well
 * as a bounded context within our domain.</p>
 * 
 * @author matt
 */
public class Contact extends AggregateRoot{

    private String firstName;
    private String lastName;
    private Address homeAddress;
    private Address workAddress;
    private EmailAddresses emailAddress;
    private PhoneNumbers phoneNumbers;
    private String aimHandle;

    public Contact(){}

    public Contact(String firstName, String lastName){
       createContact(UUID.randomUUID(), firstName, lastName);
    }
    
    public Contact(UUID identity,String firstName, String lastName){
        createContact(identity, firstName, lastName);
    }

    protected final void createContact(UUID identity, String firstName, String lastName){
        Validate.notNull(identity, "Identity cannot be null");
        Validate.notNull(firstName, "First name cannot be null");
        Validate.notNull(lastName, "Last name cannot be null");
        this.identity = identity;
        this.firstName = firstName;
        this.lastName = lastName;
        applyEvent( new ContactCreated(this.identity, this.firstName, this.lastName));
    }

    public void changeHomeAddress(final Address homeAddress){
        Validate.notNull(homeAddress, "Home address cannot be null! Use remove instead.");
        this.homeAddress = homeAddress;
        applyEvent( new HomeAddressChanged(this.identity, this.homeAddress));
    }

    public void removeHomeAddress(){
        this.homeAddress = null;
        applyEvent( new HomeAddressRemoved(this.identity));
    }

    public void changeWorkAddress(final Address workAddress){
        Validate.notNull(workAddress, "Work address cannot be set to null!");
        this.workAddress = workAddress;
        applyEvent( new WorkAddressChanged(this.identity, this.workAddress));
    }

    public void removeWorkAddress(){
        this.workAddress = null;
        applyEvent( new WorkAddressRemoved(this.identity));
    }

    public void changePhoneNumbers(final PhoneNumbers phoneNumbers){
        Validate.notNull(phoneNumbers, "Phone numbers cannot be null!");
        this.phoneNumbers = phoneNumbers;
        applyEvent( new PhoneNumbersChanged(this.identity, this.phoneNumbers));
    }

    public void changeEmailAddresses(final EmailAddresses emailAddresses){
        Validate.notNull(emailAddresses, "Email addresses cannot be null!");
        this.emailAddress = emailAddresses;
        applyEvent( new EmailAddressesChanged(this.identity,this.emailAddress));
    }

    public void changeAIMHandle(final String aimHandle){
        Validate.notNull(aimHandle, "AIM Handle cannot be null. Use remove instead.");
        this.aimHandle = aimHandle;
        applyEvent( new AIMHandleChanged(this.identity, this.aimHandle));
    }

    public void removeAIMHandle(){
        this.aimHandle = null;
        applyEvent( new AIMHandleRemoved(this.identity));
    }
    
    @Override
    protected void replay(final Event event){
        try{
            Method m = getClass().getDeclaredMethod("replay", event.getClass());
            m.invoke(this,event);
        }catch(Exception e){
            throw new ReplayFailureException("Failed to replay contact with id =>" + 
                    this.identity, e);
        }
    }

    protected void replay(final HomeAddressChanged event){
        changeHomeAddress(event.getHomeAddress());
    }

    protected void replay(final HomeAddressRemoved event){
        removeHomeAddress();
    }

    protected void replay(final WorkAddressChanged event){
        changeWorkAddress(event.getWorkAddress());
    }

    protected void replay(final WorkAddressRemoved event){
        removeWorkAddress();
    }

    protected void replay(final PhoneNumbersChanged event){
        changePhoneNumbers(event.getPhoneNumbers());
    }

    protected void replay(final EmailAddressesChanged event){
        changeEmailAddresses(event.getAddresses());
    }

    protected void replay(final AIMHandleChanged event){
        changeAIMHandle(event.getAimHandle());
    }

    protected void replay(final AIMHandleRemoved event){
        removeAIMHandle();
    }

    protected void replay(final ContactCreated event){
        createContact(event.getIdentity(),event.getFirstName(), event.getLastName());
    }

    public String getAimHandle() {
        return aimHandle;
    }

    public EmailAddresses getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    public Address getWorkAddress() {
        return workAddress;
    }



}
