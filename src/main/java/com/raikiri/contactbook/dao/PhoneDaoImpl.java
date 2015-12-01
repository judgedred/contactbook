package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.domain.Phone;
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
            CriteriaQuery<Phone> cq = em.getCriteriaBuilder().createQuery(Phone.class);
            cq.select(cq.from(Phone.class));
            return em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Phone> getPersonPhoneAll(Person person) throws DaoException
    {
        try
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Phone> cq = cb.createQuery(Phone.class);
            Root<Phone> root = cq.from(Phone.class);
            cq.select(root).where(cb.equal(root.get("person"), person));
            return em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public Phone getPhoneDefault(Person person) throws DaoException
    {
        try
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Phone> cq = cb.createQuery(Phone.class);
            Root<Phone> root = cq.from(Phone.class);
            cq.select(root).where(cb.and(cb.equal(root.get("person"), person), cb.isTrue(root.get("phoneDefault"))));
            List<Phone> resultList = em.createQuery(cq).getResultList();
            Phone phone = null;
            if(resultList.size() == 1)
            {
                phone = resultList.get(0);
            }
            return phone;
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
