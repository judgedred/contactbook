package com.raikiri.contactbook.web;


import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.service.PersonService;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestScoped
@Path("/")
public class PersonController
{
    @Inject
    private PersonService personService;

    @GET
//    @Path("/")
    @Produces("text/plain")
    public String test() throws Exception
    {
//        List<Person> contactList = personService.getPersonAll();
//        return "Hello";
//        return new Viewable("index.html");
//        String output = "Jersey say : Hi! " ;
        return "Hello, works!";
//        return Response.ok().entity(new Viewable("index.html")).build();
//        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/contactList")
//    @Produces("text/html")
    public Viewable listContacts() throws Exception
    {
//        return "Hello2 works!";
//        return Response.ok().entity(new Viewable("/contactList")).build();
        List<Person> personList = personService.getPersonAll();
        Map<String, Object> contactList = new HashMap<>();
        contactList.put("contactList", personList);
        return new Viewable("/contactList", contactList);
    }

    @GET
    @Path("/contactAdd")
    public Viewable addContact()
    {
        Map<String, Object> personModel = new HashMap<>();
        personModel.put("person", new Person());
        return new Viewable("/contactForm", personModel);
    }

    @GET
    @Path("/contactEdit/{personId}")
    public Viewable editContact(@PathParam("personId") Integer personId) throws Exception
    {
        Person person = personService.getPersonById(personId);
        if(person != null)
        {
            Map<String, Object> personModel = new HashMap<>();
            personModel.put("person", person);
            return new Viewable("/contactForm", personModel);
        }
        else
        {
            return null;
        }
    }

    @POST
    @Path("/persistContact")
    @Consumes("application/x-www-form-urlencoded")
    public Viewable persistContact(@DefaultValue("0") @FormParam("personId") Integer personId,
                                   @FormParam("personName") String personName,
                                   @FormParam("personSurname") String personSurname,
                                   @FormParam("personPatronymic") String personPatronymic,
                                   @FormParam("birthday") Date birthday) throws Exception
    {
        Person person = new Person();
        Boolean personForUpdate = false;
        if(personId != 0)
        {
            person = personService.getPersonById(personId);
            personForUpdate = true;
        }
        if(personName != null && personSurname != null
                && personPatronymic != null && birthday != null)
        {
            person.setPersonName(personName);
            person.setPersonSurname(personSurname);
            person.setPersonPatronymic(personPatronymic);
            person.setBirthday(birthday);
            if(personForUpdate)
            {
                personService.update(person);
            }
            else
            {
                personService.create(person);
            }
            return new Viewable("/contactlist");
        }
        return new Viewable("/contactlist");



        /*if(formParam != null)
        {
            for(Map.Entry<String, List<String>> entry : formParam.entrySet())
            {
                if(entry.getKey().equals("personId") && entry.getKey() != null)
                {
                    person = personService.getPersonById(Integer.parseInt(entry.getValue().get(0)));
                }
                else if(entry.getKey().equals("personName") && entry.getKey() != null)
                {
                    person.setPersonName(entry.getValue().get(0));
                }
                else if(entry.getKey().equals("personSurname") && entry.getKey() != null)
                {
                    person.setPersonSurname(entry.getValue().get(0));
                }
                else if(entry.getKey().equals("personPatronymic") && entry.getKey() != null)
                {
                    person.setPersonPatronymic(entry.getValue().get(0));
                }
                else if(entry.getKey().equals("birthday") && entry.getKey() != null)
                {
                    person.setPersonName(entry.getValue().get(0));
                }
            }
        }*/


    }

    /*@GET
    @Produces("text/html")
    @Path("/contactlist")
    public void showJSP(@Context HttpServletResponse response,
                        @Context HttpServletRequest request) throws ServletException, IOException
    {
        request.getRequestDispatcher("/index.html").forward(request, response);
    }*/

}
