package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Phone;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;
import java.util.List;

@RequestScoped
public class PhoneDaoImpl implements PhoneDao
{
    @PersistenceContext
    private EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
    public Phone create(Phone phone) throws DaoException
    {
        try
        {
            utx.begin();
            em.persist(phone);
            utx.commit();
            return phone;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public Phone update(Phone phone) throws DaoException
    {
        try
        {
            utx.begin();
            em.merge(phone);
            utx.commit();
            return em.find(Phone.class, phone.getPhoneId());
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Phone phone) throws DaoException
    {
        try
        {
            utx.begin();
            Phone addressToBeRemoved = em.getReference(Phone.class, phone.getPhoneId());
            em.remove(addressToBeRemoved);
            utx.commit();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
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

    @Override
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
