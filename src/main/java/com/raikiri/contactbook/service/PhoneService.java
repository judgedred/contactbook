package com.raikiri.contactbook.service;


import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.domain.Phone;

import java.util.List;

public interface PhoneService
{
    public Phone create(Phone phone) throws DaoException;
    public Phone update(Phone phone) throws DaoException;
    public List<Phone> getPhoneAll() throws DaoException;
    public Phone getPhoneById(int id) throws DaoException;
}
