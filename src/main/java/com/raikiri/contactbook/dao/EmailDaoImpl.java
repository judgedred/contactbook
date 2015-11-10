package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Email;

import javax.persistence.EntityManager;
import java.util.List;


public class EmailDaoImpl implements EmailDao
{
    private EntityManager em;

    public Email create(Email email) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            em.persist(email);
            em.getTransaction().commit();
            em.refresh(email);
            return email;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public Email update(Email email) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            em.merge(email);
            em.getTransaction().commit();
            return em.find(Email.class, email.getEmailId());
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public void delete(Email email) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            Email emailToBeRemoved = em.getReference(Email.class, email.getEmailId());
            em.remove(emailToBeRemoved);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public List<Email> getEmailAll() throws DaoException
    {
        /*try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Email.class));
            return (List<Email>) em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }*/
        return null;
    }

    public Email getEmailById(int id) throws DaoException
    {
        try
        {
            return em.find(Email.class, id);
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }
}
