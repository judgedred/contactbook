package com.raikiri.contactbook.service;

import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.EmailDao;
import com.raikiri.contactbook.domain.Email;
import com.raikiri.contactbook.domain.Person;

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
    public List<Email> getPersonEmailAll(Person person) throws DaoException
    {
        return emailDao.getPersonEmailAll(person);
    }

    @Override
    public boolean emailValidate(List<Email> emailList)
    {
        int emailDefaultCount = 0;
        for(Email e : emailList)
        {
            if(e.getEmailType() == null || e.getEmailValue() == null)
            {
                return false;
            }
            if(e.getEmailDefault())
            {
                emailDefaultCount++;
            }
        }
        if(emailDefaultCount != 1)
        {
            return false;
        }
        return true;
    }

    @Override
    public Email getEmailDefault(Person person) throws DaoException
    {
        return emailDao.getEmailDefault(person);
    }
}
