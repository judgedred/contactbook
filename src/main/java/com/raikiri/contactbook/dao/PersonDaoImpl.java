package com.raikiri.contactbook.dao;

import com.raikiri.contactbook.domain.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@ApplicationScoped
public class PersonDaoImpl implements PersonDao
{
    @PersistenceContext
    private EntityManager em;

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    public PersonDaoImpl()
    {

    }
}
