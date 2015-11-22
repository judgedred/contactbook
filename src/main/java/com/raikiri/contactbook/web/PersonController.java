package com.raikiri.contactbook.web;


import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Email;
import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.domain.Phone;
import com.raikiri.contactbook.service.AddressService;
import com.raikiri.contactbook.service.EmailService;
import com.raikiri.contactbook.service.PersonService;
import com.raikiri.contactbook.service.PhoneService;
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
import java.net.URI;
import java.text.SimpleDateFormat;
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

    @Inject
    private AddressService addressService;

    @Inject
    private PhoneService phoneService;

    @Inject
    private EmailService emailService;

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
        Map<String, Object> formModel = new HashMap<>();
//        formModel.put("person", new Person());
//        formModel.put("address", new Address());
        return new Viewable("/contactForm3");
    }

    @GET
    @Path("/contactEdit")
    public Viewable editContact(@QueryParam("personId") Integer personId) throws Exception
    {
        if(personId != null)
        {
            Person person = personService.getPersonById(personId);
            if(person != null)
            {

                Map<String, Object> formModel = new HashMap<>();
                formModel.put("person", person);
//            formModel.put("address", new Address());
                return new Viewable("/contactForm", formModel);
            }
        }
        return null;                                                    // NullPointer. возвращать respone.notFound?
    }

    @GET
    @Path("/contactView")
    public Viewable viewContact(@QueryParam("personId") Integer personId) throws Exception
    {
        if(personId != null)
        {
            Person person = personService.getPersonById(personId);
            if(person != null)
            {

                Map<String, Object> formModel = new HashMap<>();
                formModel.put("person", person);
//            formModel.put("address", new Address());
                return new Viewable("/contactInfo", formModel);
            }
        }
        return null;
    }

    @GET
    @Path("/contactInfo")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public ContactWrapper getContactInfo(@QueryParam("personId") Integer personId) throws Exception
    {
        if(personId != null)
        {
            Person person = personService.getPersonById(personId);
            if(person != null)
            {
                List<Address> addressList = addressService.getAddressAllById(personId);
                List<Email> emailList = emailService.getEmailAllById(personId);
                List<Phone> phoneList = phoneService.getPhoneAllById(personId);
                ContactWrapper contactWrapper = new ContactWrapper();
                contactWrapper.setPerson(person);
                contactWrapper.setAddressList(addressList);
                contactWrapper.setEmailList(emailList);
                contactWrapper.setPhoneList(phoneList);
                return contactWrapper;
            }
        }
        return null;
    }

    /*@POST
    @Path("/persistContact")
    @Consumes("application/x-www-form-urlencoded")
    public void persistContact(@DefaultValue("0") @FormParam("personId") Integer personId,
                                   @FormParam("personName") String personName,
                                   @FormParam("personSurname") String personSurname,
                                   @FormParam("personPatronymic") String personPatronymic,
                                   @FormParam("birthday") String birthdayStr,
                                   @FormParam("addressValue") String addressValue,
                                   @FormParam("addressDefault") String addreessDefault,
                                   @Context HttpServletResponse response ) throws Exception
    {
        Person person = new Person();

        Boolean personForUpdate = false;
        if(personId != 0)
        {
            person = personService.getPersonById(personId);
            personForUpdate = true;
        }
        if(!personName.isEmpty() && !personSurname.isEmpty()
                && !personPatronymic.isEmpty() && !birthdayStr.isEmpty())
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = new Date(format.parse(birthdayStr).getTime());
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
            }*/
//            return response.sendRedirect("/contactList");

//            return Response.ok().location("c")
//        }
//        response.sendRedirect("contactList");



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


//    }

    @POST
    @Path("/persistContact")
    @Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public ContactWrapper persistContact(ContactWrapper contactWrapper) throws Exception
    {
        if(contactWrapper != null)
        {
            Boolean personForUpdate = false;
            Person person = contactWrapper.getPerson();
            List<Address> addressList = contactWrapper.getAddressList();
            List<Email> emailList = contactWrapper.getEmailList();
            List<Phone> phoneList = contactWrapper.getPhoneList();
            if(person != null && person.getPersonName() != null && person.getPersonSurname() != null
                    && person.getPersonPatronymic() != null && person.getBirthday() != null)
            {
                if(addressList != null)
                {
                    if(!addressService.addressValidate(addressList))
                    {
                        return null;
                    }
                }
                if(emailList != null)
                {
                    if(!emailService.emailValidate(emailList))
                    {
                        return null;
                    }
                }
                if(phoneList != null)
                {
                    if(!phoneService.phoneValidate(phoneList))
                    {
                        return null;
                    }
                }

                if(person.getPersonId() != null)
                {
                    personForUpdate = true;
                }

                if(personForUpdate)
                {
                    Person personUpdated = personService.update(person);
                    if(addressList != null)
                    {
                        List<Address> addressPersonList = addressService.getAddressAllById(personUpdated.getPersonId());
                        for(Address a : addressList)
                        {
                            boolean addressUpdated = false;
                            for(Address ad : addressPersonList)
                            {
                                ad.setAddressValue(a.getAddressValue());
                                ad.setAddressDefault(a.getAddressDefault());
                                addressService.update(ad);
                                addressPersonList.remove(ad);
                                addressUpdated = true;
                                break;
                            }
                            if(!addressUpdated)
                            {
                                a.setPerson(personUpdated);
                                addressService.create(a);
                            }
                        }
                    }
                    if(emailList != null)
                    {
                        List<Email> emailPersonList = emailService.getEmailAllById(personUpdated.getPersonId());
                        for(Email e : emailList)
                        {
                            boolean emailUpdated = false;
                            for(Email em : emailPersonList)
                            {
                                em.setEmailType(e.getEmailType());
                                em.setEmailValue(e.getEmailValue());
                                em.setEmailDefault(e.getEmailDefault());
                                emailService.update(em);
                                emailPersonList.remove(em);
                                emailUpdated = true;
                                break;
                            }
                            if(!emailUpdated)
                            {
                                e.setPerson(personUpdated);
                                emailService.create(e);
                            }
                        }
                    }
                    if(phoneList != null)
                    {
                        List<Phone> phonePersonList = phoneService.getPhoneAllById(personUpdated.getPersonId());
                        for(Phone p : phoneList)
                        {
                            boolean phoneUpdated = false;
                            for(Phone ph : phonePersonList)
                            {
                                ph.setPhoneType(p.getPhoneType());
                                ph.setPhoneNumber(p.getPhoneNumber());
                                ph.setPhoneDefault(p.getPhoneDefault());
                                phoneService.update(ph);
                                phonePersonList.remove(ph);
                                phoneUpdated = true;
                                break;
                            }
                            if(!phoneUpdated)
                            {
                                p.setPerson(personUpdated);
                                phoneService.create(p);
                            }
                        }
                    }
                    contactWrapper.setPerson(personUpdated);
                    contactWrapper.setAddressList(addressService.getAddressAllById(personUpdated.getPersonId()));
                    contactWrapper.setEmailList(emailService.getEmailAllById(personUpdated.getPersonId()));
                    contactWrapper.setPhoneList(phoneService.getPhoneAllById(personUpdated.getPersonId()));
                }
                else
                {
                    Person personCreated = personService.create(person);
                    if(addressList != null)
                    {
                        for(Address a : addressList)
                        {
                            a.setPerson(personCreated);
                            addressService.create(a);
                        }
                    }
                    if(emailList != null)
                    {
                        for(Email e : emailList)
                        {
                            e.setPerson(personCreated);
                            emailService.create(e);
                        }
                    }
                    if(phoneList != null)
                    {
                        for(Phone p : phoneList)
                        {
                            p.setPerson(personCreated);
                            phoneService.create(p);
                        }
                    }
                    contactWrapper.setPerson(personCreated);
                    contactWrapper.setAddressList(addressService.getAddressAllById(personCreated.getPersonId()));
                    contactWrapper.setEmailList(emailService.getEmailAllById(personCreated.getPersonId()));
                    contactWrapper.setPhoneList(phoneService.getPhoneAllById(personCreated.getPersonId()));
                }
                return contactWrapper;
            }
        }
        return null;
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
