package com.raikiri.contactbook.dao;


import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Person;
import java.util.List;

public interface AddressDao
{
    public Address create(Address address) throws DaoException;
    public Address update(Address address) throws DaoException;
    public void delete(Address address) throws DaoException;
    public List<Address> getAddressAll() throws DaoException;
    public List<Address> getPersonAddressAll(Person person) throws DaoException;
    public Address getAddressDefault(Person person) throws DaoException;
    public Address getAddressById(int id) throws DaoException;
}
