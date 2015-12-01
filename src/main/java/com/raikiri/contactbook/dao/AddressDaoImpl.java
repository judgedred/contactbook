package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Person;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import java.util.List;


@RequestScoped
public class AddressDaoImpl implements AddressDao
{
    @PersistenceContext
    private EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Address> getAddressAll() throws DaoException
    {
        try
        {
            CriteriaQuery<Address> cq = em.getCriteriaBuilder().createQuery(Address.class);
            cq.select(cq.from(Address.class));
            return em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Address> getPersonAddressAll(Person person) throws DaoException
    {
        try
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Address> cq = cb.createQuery(Address.class);
            Root<Address> root = cq.from(Address.class);
            cq.select(root).where(cb.equal(root.get("person"), person));
            return em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public Address getAddressDefault(Person person) throws DaoException
    {
        try
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Address> cq = cb.createQuery(Address.class);
            Root<Address> root = cq.from(Address.class);
            cq.select(root).where(cb.and(cb.equal(root.get("person"), person), cb.isTrue(root.get("addressDefault"))));
            List<Address> resultList = em.createQuery(cq).getResultList();
            Address address = null;
            if(resultList.size() == 1)
            {
                address = resultList.get(0);
            }
            return address;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
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
