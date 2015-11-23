import com.raikiri.contactbook.dao.*;
import com.raikiri.contactbook.domain.Email;
import com.raikiri.contactbook.domain.Person;
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
public class EmailDaoTest
{
    @Deployment
    public static Archive<?> createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonDaoImpl.class, DaoException.class, PersonDao.class, Person.class,
                        Email.class, EmailDao.class, EmailDaoImpl.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private PersonDao personDao;

    @Inject
    private EmailDao emailDao;

    @Test
    public void testGetPersonById() throws DaoException
    {
        Email emailTest = emailDao.getEmailById(1);
        Assert.assertNotNull(emailTest);
    }

    @Test
    public void testCreate() throws DaoException
    {
        Email email = new Email();
        email.setEmailType("test");
        email.setEmailValue("testCreatePassed");
        email.setPerson(personDao.getPersonById(1));
        email.setEmailDefault(true);
        String emailTypeExpected = email.getEmailType();
        String emailValueExpected = email.getEmailValue();
        Person personExpected = email.getPerson();
        boolean flagExpected = email.getEmailDefault();
        Email emailTest = emailDao.create(email);
        Assert.assertNotNull(emailTest);
        String emailTypeResult = emailTest.getEmailType();
        String emailValueResult = emailTest.getEmailValue();
        Person personResult = emailTest.getPerson();
        boolean flagResult = emailTest.getEmailDefault();
        Assert.assertEquals(emailTypeExpected, emailTypeResult);
        Assert.assertEquals(emailValueExpected, emailValueResult);
        Assert.assertEquals(personExpected, personResult);
        Assert.assertEquals(flagExpected, flagResult);
    }

    @Test
    public void testUpdate() throws DaoException
    {
        Email email = new Email();
        email.setEmailId(2);
        email.setEmailType("test");
        email.setEmailValue("testUpdatePassed");
        email.setPerson(personDao.getPersonById(1));
        email.setEmailDefault(false);
        int idExpceted = email.getEmailId();
        String emailTypeExpected = email.getEmailType();
        String emailValueExpected = email.getEmailValue();
        Person personExpected = email.getPerson();
        boolean flagExpected = email.getEmailDefault();
        Email emailTest = emailDao.update(email);
        Assert.assertNotNull(emailTest);
        int idResult = emailTest.getEmailId();
        String emailTypeResult = emailTest.getEmailType();
        String emailValueResult = emailTest.getEmailValue();
        Person personResult = emailTest.getPerson();
        boolean flagResult = emailTest.getEmailDefault();
        Assert.assertEquals(idExpceted, idResult);
        Assert.assertEquals(emailTypeExpected, emailTypeResult);
        Assert.assertEquals(emailValueExpected, emailValueResult);
        Assert.assertEquals(personExpected, personResult);
        Assert.assertEquals(flagExpected, flagResult);
    }

    @Test
    public void testDelete() throws DaoException
    {
        Email email = emailDao.getEmailById(3);
        emailDao.delete(email);
        Assert.assertNull(emailDao.getEmailById(email.getEmailId()));
    }

    @Test
    public void testGetAll() throws DaoException
    {
        List<Email> listTest = emailDao.getEmailAll();
        Assert.assertNotNull(listTest);
        Assert.assertTrue(listTest.size() > 0);
    }

    @After
    public void cleanUp() throws DaoException
    {
        List<Email> lst = emailDao.getEmailAll();
        for(Email e : lst)
        {
            if(e.getEmailValue().equals("testCreatePassed") || e.getEmailValue().equals("testUpdatePassed"))
            {
                emailDao.delete(e);
            }
        }
    }
}
