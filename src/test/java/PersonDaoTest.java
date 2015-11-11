import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.PersonDao;
import com.raikiri.contactbook.dao.PersonDaoImpl;
import com.raikiri.contactbook.domain.Person;
import org.junit.Assert;
import org.junit.Test;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


public class PersonDaoTest
{
    /*@Inject
    private PersonDao personDao;*/

    @Test
    public void testGetPersonById() throws DaoException
    {
        PersonDao personDao = new PersonDaoImpl();
        Person personTest = personDao.getPersonById(1);
        Assert.assertNotNull(personTest);
    }
}
