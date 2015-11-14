package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Phone;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class PhoneDaoImpl implements PhoneDao
{
    private EntityManager em;

    public Phone create(Phone phone) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
            em.refresh(phone);
            return phone;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public Phone update(Phone phone) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            em.merge(phone);
            em.getTransaction().commit();
            return em.find(Phone.class, phone.getPhoneId());
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public void delete(Phone phone) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            Phone addressToBeRemoved = em.getReference(Phone.class, phone.getPhoneId());
            em.remove(addressToBeRemoved);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public List<Phone> getPhoneAll() throws DaoException
    {
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Phone.class));
            return (List<Phone>) em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public Phone getPhoneById(int id) throws DaoException
    {
        try
        {
            return em.find(Phone.class, id);
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }
}
