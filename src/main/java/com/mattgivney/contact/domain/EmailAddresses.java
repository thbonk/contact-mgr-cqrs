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
public class EmailAddresses implements Serializable{

    private final String workEmailAddress;
    private final String homeEmailAddress;
    private final String alternateEmailAddress;

    public EmailAddresses(final String workEmailAddress,
                          final String homeEmailAddress,
                          final String alternateEmailAddress) {
        this.workEmailAddress = workEmailAddress;
        this.homeEmailAddress = homeEmailAddress;
        this.alternateEmailAddress = alternateEmailAddress;
    }

    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    public String getHomeEmailAddress() {
        return homeEmailAddress;
    }

    public String getWorkEmailAddress() {
        return workEmailAddress;
    }

    @Override
    public String toString() {
        return "EmailAddresses{" + "workEmailAddress=" +
                workEmailAddress + "homeEmailAddress=" +
                homeEmailAddress + "alternateEmailAddress=" +
                alternateEmailAddress + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmailAddresses other = (EmailAddresses) obj;
        if ((this.workEmailAddress == null) ? (other.workEmailAddress != null) : !this.workEmailAddress.equals(other.workEmailAddress)) {
            return false;
        }
        if ((this.homeEmailAddress == null) ? (other.homeEmailAddress != null) : !this.homeEmailAddress.equals(other.homeEmailAddress)) {
            return false;
        }
        if ((this.alternateEmailAddress == null) ? (other.alternateEmailAddress != null) : !this.alternateEmailAddress.equals(other.alternateEmailAddress)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.workEmailAddress != null ? this.workEmailAddress.hashCode() : 0);
        hash = 47 * hash + (this.homeEmailAddress != null ? this.homeEmailAddress.hashCode() : 0);
        hash = 47 * hash + (this.alternateEmailAddress != null ? this.alternateEmailAddress.hashCode() : 0);
        return hash;
    }



}
