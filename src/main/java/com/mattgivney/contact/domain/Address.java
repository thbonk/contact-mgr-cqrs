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
public class Address implements Serializable{

    private final String street;
    private final String suite;
    private final String city;
    private final String state;
    private final String postalCode;

    public Address(final String street, final String suite, final String city,
                   final String state, final String postalCode) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    @Override
    public String toString() {
        return "Address{" + "street=" + street + "suite=" + suite +
               "city=" + city + "state=" + state + "postalCode=" +
               postalCode + '}';
    }
}
