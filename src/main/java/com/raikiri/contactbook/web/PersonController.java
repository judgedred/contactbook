package com.raikiri.contactbook.web;


import com.raikiri.contactbook.service.PersonService;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.inject.Inject;
import javax.ws.rs.Path;

public class PersonController
{
    @Inject
    private PersonService personService;

    @Path("/contactList")
    public Viewable listContacts() throws Exception
    {
        return null;
    }

}
