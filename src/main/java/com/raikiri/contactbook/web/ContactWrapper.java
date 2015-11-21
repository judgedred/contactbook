package com.raikiri.contactbook.web;


import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.service.AddressService;
import com.raikiri.contactbook.service.PersonService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;


public class ContactWrapper
{
    private Person person;
    private List<Address> addressList;

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public List<Address> getAddressList()
    {
        return addressList;
    }

    public void setAddressList(List<Address> addressList)
    {
        this.addressList = addressList;
    }
}
