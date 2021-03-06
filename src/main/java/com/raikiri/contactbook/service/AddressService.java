package com.raikiri.contactbook.service;


import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Person;

import java.util.List;

public interface AddressService
{
    public Address create(Address address) throws DaoException;
    public Address update(Address address) throws DaoException;
    public List<Address> getPersonAddressAll(Person person) throws DaoException;
    public boolean addressValidate(List<Address> addressList);
    public Address getAddressDefault(Person person) throws DaoException;
}
