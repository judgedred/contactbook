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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.List;

@RequestScoped
@Path("/")
public class ContactController
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
    @Path("/contactList")
    @Produces("text/html;charset=utf-8")
    public Viewable viewContactList() throws Exception
    {
        List<Person> personList = personService.getPersonAll();
        Map<String, Object> contactList = new HashMap<>();
        contactList.put("contactList", personList);
        return new Viewable("/contactList", contactList);
    }

    @GET
    @Path("/contactListGet")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public List<ContactWrapperDefault> getContactList() throws Exception
    {
        List<ContactWrapperDefault> contactList = new ArrayList<>();
        List<Person> personList = personService.getPersonAll();
        for(Person p : personList)
        {
            ContactWrapperDefault contactWrapperDefault = new ContactWrapperDefault();
            contactWrapperDefault.setPerson(p);
            Address address = addressService.getAddressDefault(p);
            if(address != null)
            {
                contactWrapperDefault.setDefaultAddress(address);
            }
            Email email = emailService.getEmailDefault(p);
            if(email != null)
            {
                contactWrapperDefault.setDefaultEmail(email);
            }
            Phone phone = phoneService.getPhoneDefault(p);
            if(phone != null)
            {
                contactWrapperDefault.setDefaultPhone(phone);
            }
            contactList.add(contactWrapperDefault);
        }
        Collections.sort(contactList, new Comparator<ContactWrapperDefault>()
        {
            @Override
            public int compare(ContactWrapperDefault o1, ContactWrapperDefault o2)
            {
                return o1.getPerson().getPersonId().compareTo(o2.getPerson().getPersonId());
            }
        });
        return contactList;
    }

    @GET
    @Path("/contactAdd")
    public Viewable addContact()
    {
        return new Viewable("/contactForm");
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
                return new Viewable("/contactForm", formModel);
            }
        }
        return new Viewable("/contactList");
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
                return new Viewable("/contactInfo", formModel);
            }
        }
        return new Viewable("/contactList");
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
                List<Address> addressList = addressService.getPersonAddressAll(person);
                List<Email> emailList = emailService.getPersonEmailAll(person);
                List<Phone> phoneList = phoneService.getPersonPhoneAll(person);
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
                        List<Address> addressPersonList = addressService.getPersonAddressAll(personUpdated);
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
                        List<Email> emailPersonList = emailService.getPersonEmailAll(personUpdated);
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
                        List<Phone> phonePersonList = phoneService.getPersonPhoneAll(personUpdated);
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
                    contactWrapper.setAddressList(addressService.getPersonAddressAll(personUpdated));
                    contactWrapper.setEmailList(emailService.getPersonEmailAll(personUpdated));
                    contactWrapper.setPhoneList(phoneService.getPersonPhoneAll(personUpdated));
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
                    contactWrapper.setAddressList(addressService.getPersonAddressAll(personCreated));
                    contactWrapper.setEmailList(emailService.getPersonEmailAll(personCreated));
                    contactWrapper.setPhoneList(phoneService.getPersonPhoneAll(personCreated));
                }
                return contactWrapper;
            }
        }
        return null;
    }
}
