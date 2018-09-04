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

import java.io.Serializable;

/**
 *
 * @author matt
 */
public class PhoneNumbers implements Serializable {

    private final String homePhoneNumber;
    private final String workPhoneNumber;
    private final String mobilePhoneNumber;
    private final String alternatePhoneNumber;

    public PhoneNumbers(final String homePhoneNumber,
                        final String workPhoneNumber,
                        final String mobilePhoneNumber,
                        final String alternatePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        this.workPhoneNumber = workPhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.alternatePhoneNumber = alternatePhoneNumber;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    @Override
    public String toString() {
        return "PhoneNumbers{" + "homePhoneNumber=" + homePhoneNumber +
                "workPhoneNumber=" + workPhoneNumber + "mobilePhoneNumber=" +
                mobilePhoneNumber + "alternatePhoneNumber=" +
                alternatePhoneNumber + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhoneNumbers other = (PhoneNumbers) obj;
        if ((this.homePhoneNumber == null) ? (other.homePhoneNumber != null) : !this.homePhoneNumber.equals(other.homePhoneNumber)) {
            return false;
        }
        if ((this.workPhoneNumber == null) ? (other.workPhoneNumber != null) : !this.workPhoneNumber.equals(other.workPhoneNumber)) {
            return false;
        }
        if ((this.mobilePhoneNumber == null) ? (other.mobilePhoneNumber != null) : !this.mobilePhoneNumber.equals(other.mobilePhoneNumber)) {
            return false;
        }
        if ((this.alternatePhoneNumber == null) ? (other.alternatePhoneNumber != null) : !this.alternatePhoneNumber.equals(other.alternatePhoneNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (this.homePhoneNumber != null ? this.homePhoneNumber.hashCode() : 0);
        hash = 19 * hash + (this.workPhoneNumber != null ? this.workPhoneNumber.hashCode() : 0);
        hash = 19 * hash + (this.mobilePhoneNumber != null ? this.mobilePhoneNumber.hashCode() : 0);
        hash = 19 * hash + (this.alternatePhoneNumber != null ? this.alternatePhoneNumber.hashCode() : 0);
        return hash;
    }

    
}
