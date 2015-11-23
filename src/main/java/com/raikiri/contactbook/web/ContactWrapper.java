package com.raikiri.contactbook.web;


import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Email;
import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.domain.Phone;
import java.util.List;


public class ContactWrapper
{
    private Person person;
    private List<Address> addressList;
    private List<Email> emailList;
    private List<Phone> phoneList;

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

    public List<Email> getEmailList()
    {
        return emailList;
    }

    public void setEmailList(List<Email> emailList)
    {
        this.emailList = emailList;
    }

    public List<Phone> getPhoneList()
    {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList)
    {
        this.phoneList = phoneList;
    }
}
