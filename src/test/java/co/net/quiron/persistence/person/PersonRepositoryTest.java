package co.net.quiron.persistence.person;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.person.Person;
import co.net.quiron.domain.person.PersonType;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Person repository tester.
 */
class PersonRepositoryTest {

    IAppRepository<Person> personRepository;
    IAppRepository<PersonType> personTypeRepository;

    /**
     * Initializes repositorys and data for the test.
     */
    @BeforeEach
    void setUp() {

        personRepository = RepositoryFactory.getDBContext(Person.class);
        personTypeRepository = RepositoryFactory.getDBContext(PersonType.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get person by id.
     */
    @Test
    void testGetPersonById() {
        Person person = personRepository.get(3);
        String firstName = person.getFirstName();
        assertEquals("Yesid", firstName);
    }

    /**
     * Test get all persons.
     */
    @Test
    void testGetAllPersons() {
        List<Person> personList = personRepository.getList();
        assertEquals(3, personList.size());
    }

    /**
     * Test create person.
     */
    @Test
    void testCreatePerson() {

        String firstName = "John";
        String lastName = "Smith";
        PersonType personType =  personTypeRepository.get(3);

        Person person = new Person(personType, firstName, lastName);
        Person insertedPerson = personRepository.create(person);

        assertNotNull(insertedPerson);
        assertEquals(6, insertedPerson.getId());
        assertEquals("John", insertedPerson.getFirstName());
        assertEquals("Smith", insertedPerson.getLastName());
    }
}