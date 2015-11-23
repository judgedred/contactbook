import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raikiri.contactbook.dao.DaoException;
import com.raikiri.contactbook.dao.EmailDao;
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
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.Filterable;
import com.sun.jersey.api.json.JSONConfiguration;
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
import org.omg.CORBA.portable.InputStream;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(Arquillian.class)
public class ContactControllerTest
{

    @Deployment
    public static Archive<?> createDeployment()
    {
        File[] libs = Maven.resolver()
                .loadPomFromFile("pom.xml").resolve("org.glassfish.jersey.core:jersey-client")
                .withTransitivity().as(File.class);

        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonDaoImpl.class, DaoException.class, PersonDao.class, Person.class, Client.class,
                        ClientHandler.class, PersonService.class, WebResource.class, ClientResponse.class, ObjectMapper.class,
                        ContactWrapper.class, RequestBuilder.class, UniformInterface.class, Filterable.class,
                        PersonServiceImpl.class, Errors.class, ClientConfig.class, FeaturesAndProperties.class,
                        ContactWrapperDefault.class, Address.class, Email.class, Phone.class, DateAdapter.class,
                        LazyVal.class, ClassWriter.class)
                .addAsLibraries(libs)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private PersonService personService;


    @Test
    public void getContactInfo() throws IOException, DaoException
    {
        /*CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8080/contactbook/contactInfo?personId=1");
        HttpResponse response = httpClient.execute(request);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        ObjectMapper mapper = new ObjectMapper();
        List<Inquiry> inquiryListTest = mapper.readValue(br, mapper.getTypeFactory().constructCollectionType(List.class, Inquiry.class));
        Assert.assertNotNull(inquiryListTest);
        Assert.assertTrue(inquiryListTest.size() > 0);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        httpClient.close();*/
        Person personExpected = personService.getPersonById(1);
        String birthdayExpected = new SimpleDateFormat("yyyy-MM-dd").format(personExpected.getBirthday());
        /*int idExpected = person.getPersonId();
        String personNameExpected = person.getPersonName();
        String personSurnameExpected = person.getPersonSurname();
        String personPatronymicExpected = person.getPersonPatronymic();
        Date birthdayExpected = person.getBirthday();*/
        Client client = Client.create();

        WebResource webResource = client
                .resource("http://localhost:8080/contactbook/contactInfo");

//        String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

        /*ClientResponse response = webResource.type("application/json")
                .get(ClientResponse.class);*/
//        ContactWrapper contactWrapperTest = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<ContactWrapper>() {});
        ClientResponse response = webResource.queryParam("personId", "1").get(ClientResponse.class);

        /*if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }*/

        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        ContactWrapper contactWrapperTest = mapper.readValue(br, ContactWrapper.class);
        Person personResult = contactWrapperTest.getPerson();
        String birthdayResult = new SimpleDateFormat("yyyy-MM-dd").format(personResult.getBirthday());


//        String output = response.getEntity(String.class);
//        ContactWrapper contactWrapperTest = response.getEntity(ContactWrapper.class);
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
        WebResource webResource = client
     .resource("http://localhost:8080/contactbook/contactListGet");
    ClientResponse response = webResource.get(ClientResponse.class);
    BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
    ObjectMapper mapper = new ObjectMapper();
    List<ContactWrapperDefault> contactListTest = mapper.readValue(br, mapper.getTypeFactory().constructCollectionType(List.class, ContactWrapperDefault.class));
    Assert.assertNotNull(contactListTest);
    Assert.assertTrue(contactListTest.size() > 0);
}

    @Test
    public void persistContactTest() throws IOException, DaoException
    {
        Person person = new Person();
        person.setPersonName("testCreate");
        person.setPersonSurname("testCreate");
        person.setPersonPatronymic("testCreate");
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.NOVEMBER, 11);
        person.setBirthday(cal.getTime());
        Address address = new Address();
        address.setAddressValue("testCreate");
        address.setPerson(person);
        address.setAddressDefault(true);
        Email email = new Email();
        email.setEmailType("testCreate");
        email.setEmailValue("testCreate");
        email.setPerson(person);
        email.setEmailDefault(true);
        Phone phone = new Phone();
        phone.setPhoneType("testCreate");
        phone.setPhoneNumber(555777L);
        phone.setPerson(person);
        phone.setPhoneDefault(true);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        List<Email> emailList = new ArrayList<>();
        emailList.add(email);
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);
        ContactWrapper contactWrapper = new ContactWrapper();
        contactWrapper.setPerson(person);
        contactWrapper.setAddressList(addressList);
        contactWrapper.setEmailList(emailList);
        contactWrapper.setPhoneList(phoneList);
        String personNameExpected = "testCreate";
        String personSurnameExpected = "testCreate";
        String personPatronymicExpected = "testCreate";
        String birthdayExpected = "2015-11-11";
        String addressValueExpceted = "testCreate";
        Boolean addressDefaultExpected = true;
        String emailValueExpected = "testCreate";
        Long phoneNumberExpected = 555777L;
//        ObjectWriter ow = new ObjectMapper().writer();
//        String contactWrapperJson = ow.writeValueAsString(contactWrapper);
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
//        String json = response.getEntity(String.class);
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
