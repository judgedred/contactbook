package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Email;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;
import java.util.List;

@RequestScoped
public class EmailDaoImpl implements EmailDao
{
    @PersistenceContext
    private EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
    public Email create(Email email) throws DaoException
    {
        try
        {
            utx.begin();
            em.persist(email);
            utx.commit();
            return email;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public Email update(Email email) throws DaoException
    {
        try
        {
            utx.begin();
            em.merge(email);
            utx.commit();
            return em.find(Email.class, email.getEmailId());
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Email email) throws DaoException
    {
        try
        {
            utx.begin();
            Email emailToBeRemoved = em.getReference(Email.class, email.getEmailId());
            em.remove(emailToBeRemoved);
            utx.commit();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Email> getEmailAll() throws DaoException
    {
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Email.class));
            return (List<Email>) em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
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
