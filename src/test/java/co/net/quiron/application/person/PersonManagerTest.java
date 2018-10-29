package co.net.quiron.application.person;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.Person;
import co.net.quiron.domain.person.PersonType;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Person manager tester.
 */
class PersonManagerTest {

    PersonManager personManager;
    PersonTypeManager personTypeManager;

    /**
     * Initializes managers and data for the test.
     */
    @BeforeEach
    void setUp() {

        personManager = new PersonManager();
        personTypeManager = new PersonTypeManager();

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get person by id.
     */
    @Test
    void testGetPersonById() {
        Person person = personManager.get(1);
        String firstName = person.getFirstName();
        assertEquals("Yesid", firstName);
    }

    /**
     * Test get all persons.
     */
    @Test
    void testGetAllPersons() {
        List<Person> personList = personManager.getList();
        assertEquals(2, personList.size());
    }

    /**
     * Test create person.
     */

    @Test
    void testCreatePerson() {

        String firstName = "John";
        String lastName = "Smith";
        PersonType personType =  personTypeManager.get(3);

        Person person = new Person(personType, firstName, lastName);
        Person insertedPerson = personManager.create(person);

        assertNotNull(insertedPerson);
        assertEquals(3, insertedPerson.getId());
        assertEquals("John", insertedPerson.getFirstName());
        assertEquals("Smith", insertedPerson.getLastName());

    }


}