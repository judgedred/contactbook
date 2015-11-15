package com.raikiri.contactbook.service;

import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.EmailDao;
import com.raikiri.contactbook.domain.Email;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class EmailServiceImpl implements EmailService
{
    @Inject
    private EmailDao emailDao;

    @Override
    public Email create(Email email) throws DaoException
    {
        return emailDao.create(email);
    }

    @Override
    public Email update(Email email) throws DaoException
    {
        return emailDao.update(email);
    }

    @Override
    public List<Email> getEmailAll() throws DaoException
    {
        return emailDao.getEmailAll();
    }

    @Override
    public Email getEmailById(int id) throws DaoException
    {
        return emailDao.getEmailById(id);
    }
}
