import com.raikiri.contactbook.dao.*;
import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.domain.Phone;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class PhoneDaoTest
{
    @Deployment
    public static Archive<?> createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonDaoImpl.class, DaoException.class, PersonDao.class, Person.class,
                        Phone.class, PhoneDao.class, PhoneDaoImpl.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private PersonDao personDao;

    @Inject
    private PhoneDao phoneDao;

    @Test
    public void testGetPersonById() throws DaoException
    {
        Phone phoneTest = phoneDao.getPhoneById(1);
        Assert.assertNotNull(phoneTest);
    }

    @Test
    public void testCreate() throws DaoException
    {
        Phone phone = new Phone();
        phone.setPhoneType("testCreatePassed");
        phone.setPhoneNumber(5553434L);
        phone.setPerson(personDao.getPersonById(1));
        phone.setPhoneDefault(true);
        String phoneTypeExpected = phone.getPhoneType();
        Long phoneNumberExpected = phone.getPhoneNumber();
        Person personExpected = phone.getPerson();
        boolean flagExpected = phone.getPhoneDefault();
        Phone phoneTest = phoneDao.create(phone);
        Assert.assertNotNull(phoneTest);
        String phoneTypeResult = phoneTest.getPhoneType();
        Long phoneNumberResult = phoneTest.getPhoneNumber();
        Person personResult = phoneTest.getPerson();
        boolean flagResult = phoneTest.getPhoneDefault();
        Assert.assertEquals(phoneTypeExpected, phoneTypeResult);
        Assert.assertEquals(phoneNumberExpected, phoneNumberResult);
        Assert.assertEquals(personExpected, personResult);
        Assert.assertEquals(flagExpected, flagResult);
    }

    @Test
    public void testUpdate() throws DaoException
    {
        Phone phone = new Phone();
        phone.setPhoneId(2);
        phone.setPhoneType("testUpdatePassed");
        phone.setPhoneNumber(8883434L);
        phone.setPerson(personDao.getPersonById(1));
        phone.setPhoneDefault(true);
        int idExpceted = phone.getPhoneId();
        String phoneTypeExpected = phone.getPhoneType();
        Long phoneNumberExpected = phone.getPhoneNumber();
        Person personExpected = phone.getPerson();
        boolean flagExpected = phone.getPhoneDefault();
        Phone phoneTest = phoneDao.update(phone);
        Assert.assertNotNull(phoneTest);
        int idResult = phoneTest.getPhoneId();
        String phoneTypeResult = phoneTest.getPhoneType();
        Long phoneNumberResult = phoneTest.getPhoneNumber();
        Person personResult = phoneTest.getPerson();
        boolean flagResult = phoneTest.getPhoneDefault();
        Assert.assertEquals(idExpceted, idResult);
        Assert.assertEquals(phoneTypeExpected, phoneTypeResult);
        Assert.assertEquals(phoneNumberExpected, phoneNumberResult);
        Assert.assertEquals(personExpected, personResult);
        Assert.assertEquals(flagExpected, flagResult);
    }

    @Test
    public void testDelete() throws DaoException
    {
        Phone phone = phoneDao.getPhoneById(3);
        phoneDao.delete(phone);
        Assert.assertNull(phoneDao.getPhoneById(phone.getPhoneId()));
    }

    @Test
    public void testGetAll() throws DaoException
    {
        List<Phone> listTest = phoneDao.getPhoneAll();
        Assert.assertNotNull(listTest);
        Assert.assertTrue(listTest.size() > 0);
    }

    @After
    public void cleanUp() throws DaoException
    {
        List<Phone> lst = phoneDao.getPhoneAll();
        for(Phone p : lst)
        {
            if(p.getPhoneType().equals("testCreatePassed") || p.getPhoneType().equals("testUpdatePassed"))
            {
                phoneDao.delete(p);
            }
        }
    }
}
