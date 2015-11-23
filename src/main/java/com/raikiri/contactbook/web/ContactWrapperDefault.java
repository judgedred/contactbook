package com.raikiri.contactbook.web;


import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Email;
import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.domain.Phone;

public class ContactWrapperDefault
{
    private Person person;
    private Address defaultAddress;
    private Email defaultEmail;
    private Phone defaultPhone;

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Address getDefaultAddress()
    {
        return defaultAddress;
    }

    public void setDefaultAddress(Address defaultAddress)
    {
        this.defaultAddress = defaultAddress;
    }

    public Email getDefaultEmail()
    {
        return defaultEmail;
    }

    public void setDefaultEmail(Email defaultEmail)
    {
        this.defaultEmail = defaultEmail;
    }

    public Phone getDefaultPhone()
    {
        return defaultPhone;
    }

    public void setDefaultPhone(Phone defaultPhone)
    {
        this.defaultPhone = defaultPhone;
    }
}
