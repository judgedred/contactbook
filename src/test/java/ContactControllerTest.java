import com.fasterxml.jackson.databind.ObjectMapper;
import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.PersonDao;
import com.raikiri.contactbook.dao.PersonDaoImpl;
import com.raikiri.contactbook.domain.Address;
import com.raikiri.contactbook.domain.Email;
import com.raikiri.contactbook.domain.Person;
import com.raikiri.contactbook.domain.Phone;
import com.raikiri.contactbook.service.PersonService;
import com.raikiri.contactbook.service.PersonServiceImpl;
import com.raikiri.contactbook.web.ContactWrapper;
import com.raikiri.contactbook.web.ContactWrapperDefault;
import com.raikiri.contactbook.web.DateAdapter;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.filter.Filterable;
import com.sun.jersey.core.spi.component.ComponentProviderFactory;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProcessorFactory;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.core.util.FeaturesAndProperties;
import com.sun.jersey.core.util.LazyVal;
import com.sun.jersey.spi.inject.Errors;
import jdk.internal.org.objectweb.asm.ClassWriter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(Arquillian.class)
public class ContactControllerTest
{

    @Deployment
    public static Archive<?> createDeployment()
    {
        /*File[] libs = Maven.resolver()
                .loadPomFromFile("pom.xml").resolve("org.glassfish.jersey.core:jersey-client")
                .withTransitivity().as(File.class);*/

        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonDaoImpl.class, DaoException.class, PersonDao.class, Person.class, Client.class,
                        ClientHandler.class, PersonService.class, WebResource.class, ClientResponse.class, ObjectMapper.class,
                        ContactWrapper.class, RequestBuilder.class, UniformInterface.class, Filterable.class,
                        PersonServiceImpl.class, Errors.class, ClientConfig.class, FeaturesAndProperties.class,
                        ContactWrapperDefault.class, Address.class, Email.class, Phone.class, DateAdapter.class,
                        LazyVal.class, ClassWriter.class, IoCComponentProviderFactory.class, ComponentProviderFactory.class,
                        IoCComponentProcessorFactory.class)
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importDependencies(ScopeType.TEST, ScopeType.PROVIDED, ScopeType.COMPILE, ScopeType.RUNTIME, ScopeType.IMPORT, ScopeType.SYSTEM)
                        .resolve().withTransitivity().asFile())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private PersonService personService;


    @Test
    public void getContactInfo() throws IOException, DaoException
    {
        Person personExpected = personService.getPersonById(1);
        String birthdayExpected = new SimpleDateFormat("yyyy-MM-dd").format(personExpected.getBirthday());
        Client client = Client.create();
        WebResource webResource = client
                .resource("http://localhost:8080/contactbook/contactInfo");
        ClientResponse response = webResource.queryParam("personId", "1").get(ClientResponse.class);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        ContactWrapper contactWrapperTest = mapper.readValue(br, ContactWrapper.class);
        Person personResult = contactWrapperTest.getPerson();
        String birthdayResult = new SimpleDateFormat("yyyy-MM-dd").format(personResult.getBirthday());

        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertNotNull(contactWrapperTest);
        Assert.assertEquals(personExpected.getPersonId(), personResult.getPersonId());
        Assert.assertEquals(personExpected.getPersonName(), personResult.getPersonSurname());
        Assert.assertEquals(personExpected.getPersonPatronymic(), personResult.getPersonPatronymic());
        Assert.assertEquals(birthdayExpected, birthdayResult);




    }

    @Test
    public void getContactListTest() throws IOException, DaoException
    {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8080/contactbook/contactListGet");
        ClientResponse response = webResource.get(ClientResponse.class);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        List<ContactWrapperDefault> contactListTest = mapper.readValue(br, mapper.getTypeFactory().constructCollectionType(List.class, ContactWrapperDefault.class));
        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertNotNull(contactListTest);
        Assert.assertTrue(contactListTest.size() > 0);
}

    @Test
    public void persistContactTest() throws IOException, DaoException
    {
        String personNameExpected = "testCreate";
        String personSurnameExpected = "testCreate";
        String personPatronymicExpected = "testCreate";
        String birthdayExpected = "2015-11-11";
        String addressValueExpceted = "testCreate";
        Boolean addressDefaultExpected = true;
        String emailValueExpected = "testCreate";
        Long phoneNumberExpected = 555777L;
        String contactWrapperJson = "{\"person\":\n" +
                "\n" +
                "{\"personId\":null,\"personName\":\"testCreate\",\"personSurname\":\"testCreate\",\"personPatronymic\":\"testCreate\",\"birthday\":\"2015-11-11\"},\"addressList\":[{\"addressId\":null,\"addressValue\":\"testCreate\",\"addressDefault\":true,\"person\":\n" +
                "\n" +
                "{\"personId\":null,\"personName\":\"testCreate\",\"personSurname\":\"testCreate\",\"personPatronymic\":\"testCreate\",\"birthday\":\"2015-11-11\"}}],\"emailList\":[{\"emailId\":null,\"emailType\":\"testCreate\",\"emailValue\":\"testCreate\",\"emailDefault\":true,\"person\":\n" +
                "\n" +
                "{\"personId\":null,\"personName\":\"testCreate\",\"personSurname\":\"testCreate\",\"personPatronymic\":\"testCreate\",\"birthday\":\"2015-11-11\"}}],\"phoneList\":[{\"phoneId\":null,\"phoneType\":\"testCreate\",\"phoneNumber\":555777,\"phoneDefault\":true,\"person\":\n" +
                "\n" +
                "{\"personId\":null,\"personName\":\"testCreate\",\"personSurname\":\"testCreate\",\"personPatronymic\":\"testCreate\",\"birthday\":\"2015-11-11\"}}]}";
        Client client = Client.create();
        WebResource webResource = client
                .resource("http://localhost:8080/contactbook/persistContact");
        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, contactWrapperJson);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        ContactWrapper contactWrapperTest = mapper.readValue(br, ContactWrapper.class);
        Assert.assertNotNull(contactWrapperTest);
        String personNameResult = contactWrapperTest.getPerson().getPersonName();
        String personSurnameResult = contactWrapperTest.getPerson().getPersonSurname();
        String personPatronymicResult = contactWrapperTest.getPerson().getPersonPatronymic();
        String birthdayResult = new SimpleDateFormat("yyyy-MM-dd").format(contactWrapperTest.getPerson().getBirthday());
        String addressValueResult = contactWrapperTest.getAddressList().get(0).getAddressValue();
        Boolean addressDefaultResult = contactWrapperTest.getAddressList().get(0).getAddressDefault();
        String emailValueResult = contactWrapperTest.getEmailList().get(0).getEmailValue();
        Long phoneNumberResult = contactWrapperTest.getPhoneList().get(0).getPhoneNumber();
        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertEquals(personNameExpected, personNameResult);
        Assert.assertEquals(personSurnameExpected, personSurnameResult);
        Assert.assertEquals(personPatronymicExpected, personPatronymicResult);
        Assert.assertEquals(birthdayExpected, birthdayResult);
        Assert.assertEquals(addressValueExpceted, addressValueResult);
        Assert.assertEquals(addressDefaultExpected, addressDefaultResult);
        Assert.assertEquals(emailValueExpected, emailValueResult);
        Assert.assertEquals(phoneNumberExpected, phoneNumberResult);
    }

}
