package com.raikiri.contactbook.service;

import com.raikiri.contactbook.dao.AddressDao;
import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.domain.Address;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
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
    public List<Address> getAddressAll() throws DaoException
    {
        return addressDao.getAddressAll();
    }

    @Override
    public Address getAddressById(int id) throws DaoException
    {
        return addressDao.getAddressById(id);
    }

    @Override
    public List<Address> getAddressAllById(int personId) throws DaoException
    {
        List<Address> addressList = addressDao.getAddressAll();
        List<Address> filteredAddressList = new ArrayList<>();
        if(addressList != null)
        {
            for(Address a : addressList)
            {
                if(a.getPerson().getPersonId() == personId)
                {
                    filteredAddressList.add(a);
                }
            }
            return filteredAddressList;
        }
        else
        {
            return null;
        }
    }
}
