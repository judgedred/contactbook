import com.raikiri.contactbook.dao.*;
import com.raikiri.contactbook.domain.Address;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RunWith(Arquillian.class)
public class AddressDaoTest
{
    @Deployment
    public static Archive<?> createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonDaoImpl.class, DaoException.class, PersonDao.class, Person.class,
                        Address.class, AddressDao.class, AddressDaoImpl.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private PersonDao personDao;

    @Inject
    private AddressDao addressDao;

    @Test
    public void testGetPersonById() throws DaoException
    {
        Address addressTest = addressDao.getAddressById(1);
        Assert.assertNotNull(addressTest);
    }

    @Test
    public void testCreate() throws DaoException
    {
        Address address = new Address();
        address.setAddressValue("testCreatePassed");
        address.setPerson(personDao.getPersonById(1));
        address.setAddressDefault(true);
        String addressValueExpected = address.getAddressValue();
        Person personExpected = address.getPerson();
        boolean flagExpected = address.getAddressDefault();
        Address addressTest = addressDao.create(address);
        Assert.assertNotNull(addressTest);
        String addressValueResult = addressTest.getAddressValue();
        Person personResult = addressTest.getPerson();
        boolean flagResult = addressTest.getAddressDefault();
        Assert.assertEquals(addressValueExpected, addressValueResult);
        Assert.assertEquals(personExpected, personResult);
        Assert.assertEquals(flagExpected, flagResult);
    }

    @Test
    public void testUpdate() throws DaoException
    {
        Address address = new Address();
        address.setAddressId(2);
        address.setAddressValue("testUpdatePassed");
        address.setPerson(personDao.getPersonById(1));
        address.setAddressDefault(false);
        int idExpceted = address.getAddressId();
        String addressValueExpected = address.getAddressValue();
        Person personExpected = address.getPerson();
        boolean flagExpected = address.getAddressDefault();
        Address addressTest = addressDao.update(address);
        Assert.assertNotNull(addressTest);
        int idResult = addressTest.getAddressId();
        String addressValueResult = addressTest.getAddressValue();
        Person personResult = address.getPerson();
        boolean flagResult = addressTest.getAddressDefault();
        Assert.assertEquals(idExpceted, idResult);
        Assert.assertEquals(addressValueExpected, addressValueResult);
        Assert.assertEquals(personExpected, personResult);
        Assert.assertEquals(flagExpected, flagResult);
    }

    @Test
    public void testDelete() throws DaoException
    {
        Address address = addressDao.getAddressById(3);
        addressDao.delete(address);
        Assert.assertNull(addressDao.getAddressById(address.getAddressId()));
    }

    @Test
    public void testGetAll() throws DaoException
    {
        List<Address> listTest = addressDao.getAddressAll();
        Assert.assertNotNull(listTest);
        Assert.assertTrue(listTest.size() > 0);
    }

    @After
    public void cleanUp() throws DaoException
    {
        List<Address> lst = addressDao.getAddressAll();
        for(Address a : lst)
        {
            if(a.getAddressValue().equals("testCreatePassed") || a.getAddressValue().equals("testUpdatePassed"))
            {
                addressDao.delete(a);
            }
        }
    }
}
