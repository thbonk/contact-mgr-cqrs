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

import java.util.UUID;

/**
 * Mediates between the domain and data mapping
 * layers using a collection-like interface for
 * accessing contacts objects.
 * 
 * @author matt
 */
public interface ContactRepository {
    void store(Contact contact);
    Contact load(UUID aggregateId);
}
