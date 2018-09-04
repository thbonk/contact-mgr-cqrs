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
package com.mattgivney.contact.view;

import com.mattgivney.contact.domain.Address;
import com.mattgivney.contact.domain.EmailAddresses;
import com.mattgivney.contact.domain.PhoneNumbers;
import java.io.Serializable;

/**
 *
 * @author matt
 */
public class ContactDTO implements Serializable{

    private String contactId;
    private String firstName;
    private String lastName;
    private Address workAddress;
    private Address homeAddress;
    private EmailAddresses emailAddresses;
    private PhoneNumbers phoneNumbers;
    private String aimHandle;

    public ContactDTO(){ }

    public ContactDTO(String contactId){
        this.contactId = contactId;
    }

    public String getAimHandle() {
        return aimHandle;
    }

    public void setAimHandle(String aimHandle) {
        this.aimHandle = aimHandle;
    }

    public EmailAddresses getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(EmailAddresses emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }


}
