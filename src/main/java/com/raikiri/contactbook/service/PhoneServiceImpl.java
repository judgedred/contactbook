package com.raikiri.contactbook.service;

import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.PhoneDao;
import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.domain.Phone;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class PhoneServiceImpl implements PhoneService
{
    @Inject
    private PhoneDao phoneDao;

    @Override
    public Phone create(Phone phone) throws DaoException
    {
        return phoneDao.create(phone);
    }

    @Override
    public Phone update(Phone phone) throws DaoException
    {
        return phoneDao.update(phone);
    }

    @Override
    public List<Phone> getPersonPhoneAll(Person person) throws DaoException
    {
        return phoneDao.getPersonPhoneAll(person);
    }

    @Override
    public boolean phoneValidate(List<Phone> phoneList)
    {
        int phoneDefaultCount = 0;
        for(Phone p : phoneList)
        {
            if(p.getPhoneType() == null || p.getPhoneNumber() == null)
            {
                return false;
            }
            if(p.getPhoneDefault())
            {
                phoneDefaultCount++;
            }
        }
        if(phoneDefaultCount != 1)
        {
            return false;
        }
        return true;
    }

    @Override
    public Phone getPhoneDefault(Person person) throws DaoException
    {
        return phoneDao.getPhoneDefault(person);
    }
}
