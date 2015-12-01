package com.raikiri.contactbook.service;


import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.domain.Phone;
import java.util.List;

public interface PhoneService
{
    public Phone create(Phone phone) throws DaoException;
    public Phone update(Phone phone) throws DaoException;
    public List<Phone> getPersonPhoneAll(Person person) throws DaoException;
    public boolean phoneValidate(List<Phone> phoneList);
    public Phone getPhoneDefault(Person person) throws DaoException;
}
