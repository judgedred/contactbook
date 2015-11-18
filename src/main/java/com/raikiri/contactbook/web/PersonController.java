package com.raikiri.contactbook.web;


import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.service.PersonService;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;
import java.util.List;


@Path("/")
public class PersonController
{
    /*@Inject
    private PersonService personService;*/

    @GET
//    @Path("/")
    @Produces("text/plain")
    public String listContacts() throws Exception
    {
//        List<Person> contactList = personService.getPersonAll();
//        return "Hello";
//        return new Viewable("index.html");
//        String output = "Jersey say : Hi! " ;
        return "Hello, works!";
//        return Response.ok().entity(new Viewable("index.html")).build();
//        return Response.status(200).entity(output).build();
    }

    /*@GET
    @Path("/contactlist")
    public Response test() throws Exception
    {
//        return "Hello2 works!";
//        return Response.ok().entity(new Viewable("/index.html")).build();
        return Response.f
    }*/

    @GET
    @Produces("text/html")
    @Path("/contactlist")
    public void showJSP(@Context HttpServletResponse response,
                        @Context HttpServletRequest request) throws ServletException, IOException
    {
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

}
