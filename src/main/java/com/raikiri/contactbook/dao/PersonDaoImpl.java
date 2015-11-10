package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Person;

import javax.persistence.EntityManager;
import java.util.List;


public class PersonDaoImpl implements PersonDao
{
    private EntityManager em;

    public Person create(Person person) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            em.refresh(person);
            return person;
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public Person update(Person person) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return em.find(Person.class, person.getPersonId());
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public void delete(Person person) throws DaoException
    {
        try
        {
            em.getTransaction().begin();
            Person personToBeRemoved = em.getReference(Person.class, person.getPersonId());
            em.remove(personToBeRemoved);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }
    }

    public List<Person> getPersonAll() throws DaoException
    {
       /* try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Person.class));
            return (List<Person>) em.createQuery(cq).getResultList();
        }
        catch(Exception e)
        {
            throw new DaoException(e);
        }*/
        return null;
    }

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
