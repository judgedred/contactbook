package com.raikiri.contactbook.service;


import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.domain.Address;

import java.util.List;

public interface AddressService
{
    public Address create(Address address) throws DaoException;
    public Address update(Address address) throws DaoException;
    public List<Address> getAddressAll() throws DaoException;
    public Address getAddressById(int id) throws DaoException;
}
