package com.raikiri.contactbook.service;

import com.raikiri.contactbook.dao.AddressDao;
import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Person;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class AddressServiceImpl implements AddressService
{
    @Inject
    private AddressDao addressDao;

    @Override
    public Address create(Address address) throws DaoException
    {
        return addressDao.create(address);
    }

    @Override
    public Address update(Address address) throws DaoException
    {
        return addressDao.update(address);
    }

    @Override
    public List<Address> getPersonAddressAll(Person person) throws DaoException
    {
        return addressDao.getPersonAddressAll(person);
    }

    @Override
    public boolean addressValidate(List<Address> addressList)
    {
        int addressDefaultCount = 0;
        for(Address a : addressList)
        {
            if(a.getAddressValue() == null)
            {
                return false;
            }
            if(a.getAddressDefault())
            {
                addressDefaultCount++;
            }
        }
        if(addressDefaultCount != 1)
        {
            return false;
        }
        return true;
    }

    @Override
    public Address getAddressDefault(Person person) throws DaoException
    {
        return addressDao.getAddressDefault(person);
    }
}
