package co.net.quiron.application.person;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.Person;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonManagerTest {

    EntityManager<BusinessEntity> businessEntityManager;
    EntityManager<Person> personManager;

    @BeforeEach
    void setUp() {
        businessEntityManager = new BusinessEntityManager();
        personManager = new PersonManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    @Test
    void testGetPersonById() {
        Person person = personManager.get(1);
        String firstName = person.getFirstName();
        assertEquals("Yesid", firstName);
    }

    @Test
    void testGetAllPersons() {
        List<Person> personList = personManager.getList();
        assertEquals(2, personList.size());
    }

    @Test
    void testCreatePerson() {

        BusinessEntity newEntity = new BusinessEntity();
        BusinessEntity insertedEntity = businessEntityManager.create(newEntity);

        Person person = new Person(3, "Mr.", "John", "Smith");
        person.setEntity(insertedEntity);

        Person insertedPerson = personManager.create(person);

        assertNotNull(insertedEntity);
        assertEquals(3, insertedEntity.getId());
        assertNotNull(insertedEntity.getCreatedDate());

        assertNotNull(insertedPerson);
        assertEquals(3, insertedPerson.getId());
        assertEquals("John", insertedPerson.getFirstName());
        assertEquals("Smith", insertedPerson.getLastName());
        assertNotNull(insertedEntity.getCreatedDate());

    }

}