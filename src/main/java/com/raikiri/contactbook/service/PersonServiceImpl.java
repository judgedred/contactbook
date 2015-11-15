package com.raikiri.contactbook.service;

import com.raikiri.contactbook.dao.*;
import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Person;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class PersonServiceImpl implements PersonService
{
    @Inject
    private PersonDao personDao;

    @Override
    public Person create(Person person) throws DaoException
    {
        return personDao.create(person);
    }

    @Override
    public Person update(Person person) throws DaoException
    {
        return personDao.update(person);
    }

    @Override
    public List<Person> getPersonAll() throws DaoException
    {
        return personDao.getPersonAll();
    }

    @Override
    public Person getPersonById(int id) throws DaoException
    {
        return personDao.getPersonById(id);
    }
}
