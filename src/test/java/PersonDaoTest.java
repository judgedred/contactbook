import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.PersonDao;
import com.raikiri.contactbook.dao.PersonDaoImpl;
import com.raikiri.contactbook.domain.Person;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RunWith(Arquillian.class)
public class PersonDaoTest
{
    @Deployment
    public static Archive<?> createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonDaoImpl.class, DaoException.class, PersonDao.class, Person.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private PersonDao personDao;

    @Test
    public void testGetPersonById() throws DaoException
    {
        Person personTest = personDao.getPersonById(1);
        Assert.assertNotNull(personTest);
    }

    @Test
    public void testCreate() throws DaoException
    {
        Person person = new Person();
        person.setPersonName("testCreatePassed");
        person.setPersonSurname("testCreatePassed");
        person.setPersonPatronymic("testCreatePassed");
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.NOVEMBER, 14);
        person.setBirthday(cal.getTime());
        String personNameExpected = person.getPersonName();
        String personSurnameExpected = person.getPersonSurname();
        String personPatronymicExpected = person.getPersonPatronymic();
        String birthdayExpected = new SimpleDateFormat("yyyy-MM-dd").format(person.getBirthday());
        Person personTest = personDao.create(person);
        Assert.assertNotNull(personTest);
        String personNameResult = personTest.getPersonName();
        String personSurnameResult = personTest.getPersonSurname();
        String personPatronymicResult = personTest.getPersonPatronymic();
        String birthdayResult = new SimpleDateFormat("yyyy-MM-dd").format(personTest.getBirthday());
        Assert.assertEquals(personNameExpected, personNameResult);
        Assert.assertEquals(personSurnameExpected, personSurnameResult);
        Assert.assertEquals(personPatronymicExpected, personPatronymicResult);
        Assert.assertEquals(birthdayExpected, birthdayResult);
    }

    @Test
    public void testUpdate() throws DaoException
    {
        Person person = new Person();
        person.setPersonId(2);
        person.setPersonName("testUpdatePassed");
        person.setPersonSurname("testUpdatePassed");
        person.setPersonPatronymic("testUpdatePassed");
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.NOVEMBER, 14);
        person.setBirthday(cal.getTime());
        int idExpceted = person.getPersonId();
        String personNameExpected = person.getPersonName();
        String personSurnameExpected = person.getPersonSurname();
        String personPatronymicExpected = person.getPersonPatronymic();
        String birthdayExpected = new SimpleDateFormat("yyyy-MM-dd").format(person.getBirthday());
        Person personTest = personDao.update(person);
        Assert.assertNotNull(personTest);
        int idResult = personTest.getPersonId();
        String personNameResult = personTest.getPersonName();
        String personSurnameResult = personTest.getPersonSurname();
        String personPatronymicResult = personTest.getPersonPatronymic();
        String birthdayResult = new SimpleDateFormat("yyyy-MM-dd").format(personTest.getBirthday());
        Assert.assertEquals(idExpceted, idResult);
        Assert.assertEquals(personNameExpected, personNameResult);
        Assert.assertEquals(personSurnameExpected, personSurnameResult);
        Assert.assertEquals(personPatronymicExpected, personPatronymicResult);
        Assert.assertEquals(birthdayExpected, birthdayResult);
    }

    @Test
    public void testDelete() throws DaoException
    {
        Person person = personDao.getPersonById(3);
        personDao.delete(person);
        Assert.assertNull(personDao.getPersonById(person.getPersonId()));
    }

    @Test
    public void testGetAll() throws DaoException
    {
        List<Person> listTest = personDao.getPersonAll();
        Assert.assertNotNull(listTest);
        Assert.assertTrue(listTest.size() > 0);
    }

    @After
    public void cleanUp() throws DaoException
    {
        List<Person> lst = personDao.getPersonAll();
        for(Person p : lst)
        {
            if(p.getPersonName().equals("testCreatePassed") || p.getPersonName().equals("testUpdatePassed"))
            {
                personDao.delete(p);
            }
        }
    }
}
