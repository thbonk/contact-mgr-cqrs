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
package com.mattgivney.contact.controller;

import com.mattgivney.contact.domain.cmd.CommandSender;
import com.mattgivney.contact.domain.cmd.CreateContact;
import com.mattgivney.contact.view.ContactDTO;
import com.mattgivney.contact.view.ContactReadFacade;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author matt
 */
@Controller
@RequestMapping(value="/contacts")
public class ContactController {

    private ContactReadFacade contactReadFacade;
    private CommandSender commandSender;

    @RequestMapping(value="/list")
    public String list(ModelMap model,
                       @RequestParam(value="offset", required=false) int offset,
                       @RequestParam(value="max", required=false) int max){
        List<ContactDTO> contactDTOs =
                contactReadFacade.findAll(offset, max);
        model.addAttribute("contacts", contactDTOs);
        return "list";
    }

    @RequestMapping(value="/show/{id}")
    public String show(ModelMap model, @PathVariable(value="id") String id){
        ContactDTO contactDTO = contactReadFacade.find(id);
        model.addAttribute("contact", contactDTO);
        return "show";
    }

    @RequestMapping(value="/form/{id}")
    public String form(ModelMap model, @PathVariable(value="id") String id){
        ContactDTO contactDTO = contactReadFacade.find(id);
        model.addAttribute("contact", contactDTO);
        return "form";
    }
    
    @RequestMapping(value="/create")
    public String create(ModelMap model){
        model.addAttribute("contact", new ContactDTO());
        return "form";
    }

    @RequestMapping(value="/saveCreate", method=RequestMethod.POST)
    public String saveCreate(ModelMap model,
                             @RequestParam(value="firstName") String firstName,
                             @RequestParam(value="lastName") String lastName){
        commandSender.send(new CreateContact(firstName, lastName));
        model.addAttribute("messages",
                "Your request to create a new contact has been submitted");
        return "form";
    }
    
}
