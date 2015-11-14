package com.raikiri.contactbook.dao;


import com.raikiri.contactbook.domain.Person;

import javax.decorator.Decorator;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;


public interface PersonDao
{
    public Person create(Person person) throws DaoException;
    public Person update(Person person) throws DaoException;
    public void delete(Person person) throws DaoException;
    public List<Person> getPersonAll() throws DaoException;
    public Person getPersonById(int id) throws DaoException;
}
