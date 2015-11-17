package com.raikiri.contactbook.web;


import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.service.PersonService;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

//@Path("/")
@Path("/contactlist")
public class PersonController
{
    /*@Inject
    private PersonService personService;*/

    @GET
    @Produces("text/plain")
    public Viewable listContacts() throws Exception
    {
//        List<Person> contactList = personService.getPersonAll();
//        return "Hello";
        return new Viewable("index.html");
    }

}
