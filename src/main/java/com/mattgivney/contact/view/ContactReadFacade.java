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

import java.util.List;

/**
 * A read facade for fetching contacts from the RDBMS
 * 
 * @author matt
 */
public interface ContactReadFacade {

   public List<ContactDTO> findAll();
   public List<ContactDTO> findAll(int offset, int maxRecords);
   public List<ContactDTO> findAllWhere(String whereClause,Object... params);
   public List<ContactDTO> findAllWhere(String whereClause,Object[] params, int offset, int maxRecords);

   public ContactDTO find(String contactId);
   public ContactDTO findWhere(String whereClause, Object... params);

}
