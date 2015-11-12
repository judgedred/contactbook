import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.PersonDao;
import com.raikiri.contactbook.dao.PersonDaoImpl;
import com.raikiri.contactbook.domain.Person;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RunWith(CdiRunner.class)
@AdditionalClasses(PersonDaoImpl.class)
public class PersonDaoTest
{
    @Inject
    private PersonDao personDao;

    @Test
    public void testGetPersonById() throws DaoException
    {
        Person personTest = personDao.getPersonById(1);
        Assert.assertNotNull(personTest);
    }
}
