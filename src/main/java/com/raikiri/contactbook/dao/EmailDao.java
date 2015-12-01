package com.raikiri.contactbook.dao;


import com.raikiri.contactbook.domain.Email;
import com.raikiri.contactbook.domain.Person;

import java.util.List;

public interface EmailDao
{
    public Email create(Email email) throws DaoException;
    public Email update(Email email) throws DaoException;
    public void delete(Email email) throws DaoException;
    public List<Email> getEmailAll() throws DaoException;
    public List<Email> getPersonEmailAll(Person person) throws DaoException;
    public Email getEmailDefault(Person person) throws DaoException;
    public Email getEmailById(int id) throws DaoException;
}
