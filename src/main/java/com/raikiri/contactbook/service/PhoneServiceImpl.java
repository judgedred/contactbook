package com.raikiri.contactbook.service;

import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.PhoneDao;
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
    public List<Phone> getPhoneAll() throws DaoException
    {
        return phoneDao.getPhoneAll();
    }

    @Override
    public Phone getPhoneById(int id) throws DaoException
    {
        return phoneDao.getPhoneById(id);
    }
}
