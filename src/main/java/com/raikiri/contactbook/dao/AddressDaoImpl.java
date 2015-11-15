package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Address;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;
import java.util.List;


@RequestScoped
public class AddressDaoImpl implements AddressDao
{
    @PersistenceContext
    private EntityManager em;

    @Resource
    UserTransaction utx;

    public Address create(Address address) throws DaoException
    {
        try
        {
            utx.begin();
            em.persist(address);
            utx.commit();
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
            utx.begin();
            em.merge(address);
            utx.commit();
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
            utx.begin();
            Address addressToBeRemoved = em.getReference(Address.class, address.getAddressId());
            em.remove(addressToBeRemoved);
            utx.commit();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public List<Address> getAddressAll() throws DaoException
    {
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Address.class));
            return (List<Address>) em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
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
