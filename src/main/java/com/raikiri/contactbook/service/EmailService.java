package com.raikiri.contactbook.service;

import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.domain.Email;
import java.util.List;

public interface EmailService
{
    public Email create(Email email) throws DaoException;
    public Email update(Email email) throws DaoException;
    public List<Email> getEmailAll() throws DaoException;
    public Email getEmailById(int id) throws DaoException;
    public List<Email> getEmailAllById(int personId) throws DaoException;
    public boolean emailValidate(List<Email> emailList);
    public Email getEmailDefault(int personId) throws DaoException;
}
