package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Person;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;
import java.util.List;


@RequestScoped
public class PersonDaoImpl implements PersonDao
{
    @PersistenceContext
    private EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
    public Person create(Person person) throws DaoException
    {
        try
        {
            utx.begin();
            em.persist(person);
            utx.commit();
            return person;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public Person update(Person person) throws DaoException
    {
        try
        {
            utx.begin();
            em.merge(person);
            utx.commit();
            return em.find(Person.class, person.getPersonId());
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Person person) throws DaoException
    {
        try
        {
            utx.begin();
            Person personToBeRemoved = em.getReference(Person.class, person.getPersonId());
            em.remove(personToBeRemoved);
            utx.commit();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Person> getPersonAll() throws DaoException
    {
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Person.class));
            return (List<Person>) em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    @Override
    public Person getPersonById(int id) throws DaoException
    {
        try
        {
            return em.find(Person.class, id);
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }
}
