package com.raikiri.contactbook.dao;


import com.raikiri.contactbook.domain.Phone;

import java.util.List;

public interface PhoneDao
{
    public Phone create(Phone phone) throws DaoException;
    public Phone update(Phone phone) throws DaoException;
    public void delete(Phone phone) throws DaoException;
    public List<Phone> getPhoneAll() throws DaoException;
    public Phone getPhoneById(int id) throws DaoException;
}
