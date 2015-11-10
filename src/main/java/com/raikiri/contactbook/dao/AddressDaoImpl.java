package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Address;

import javax.persistence.EntityManager;
import java.util.List;


public class AddressDaoImpl implements AddressDao
{
    private EntityManager em;

    public Address create(Address address) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            em.refresh(address);
            return address;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public Address update(Address address) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
            return em.find(Address.class, address.getAddressId());
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public void delete(Address address) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            Address addressToBeRemoved = em.getReference(Address.class, address.getAddressId());
            em.remove(addressToBeRemoved);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public List<Address> getAddressAll() throws DaoException
    {
        /*try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Address.class));
            return (List<Address>) em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }*/
        return null;
    }

    public Address getAddressById(int id) throws DaoException
    {
        try
        {
            return em.find(Address.class, id);
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }
}
