package com.raikiri.contactbook.service;


import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.domain.Person;

import java.util.List;

public interface PersonService
{
    public Person create(Person person) throws DaoException;
    public Person update(Person person) throws DaoException;
    public List<Person> getPersonAll() throws DaoException;
    public Person getPersonById(int id) throws DaoException;
}
