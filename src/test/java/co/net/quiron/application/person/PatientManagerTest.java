package co.net.quiron.application.person;

import co.net.quiron.application.patient.PatientManager;
import co.net.quiron.application.person.AddressManager;
import co.net.quiron.application.person.PersonTypeManager;
import co.net.quiron.domain.account.Profile;
import co.net.quiron.domain.person.*;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Patient manager tester.
 */
class PatientManagerTest {

    //EntityManager<BusinessEntity> businessEntityManager;
    //EntityManager<Person> personManager;
    PatientManager patientManager;
    AddressManager addressManager;
    PersonTypeManager personTypeManager;

    /**
     * Initializes managers and data for the test.
     */
    @BeforeEach
    void setUp() {

        //businessEntityManager = new BusinessEntityManager();
        //personManager = new PersonManager();
        patientManager = new PatientManager();
        addressManager = new AddressManager();
        personTypeManager = new PersonTypeManager();

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");

    }

    /**
     * Tests get patient by id.
     */

    @Test
    void testGetPatientById() {
        Patient patient = patientManager.get(1);
        //String firstName = patient.getPerson().getFirstName();
        String firstName = patient.getFirstName();
        assertEquals("Yesid", firstName);
    }


    /**
     * Tests get all patients.
     */
    @Test
    void testGetAllPatients() {
        List<Patient> patientList = patientManager.getList();
        assertEquals(2, patientList.size());
    }

    /**
     * Tests create patient.
     */
/*
    @Test
    void testCreatePatient() {

        BusinessEntity newEntity = new BusinessEntity();
        BusinessEntity insertedEntity = businessEntityManager.create(newEntity);

        Person person = new Person(3, "John", "Smith");
        person.setEntity(insertedEntity);
        Person insertedPerson = personManager.create(person);

        Patient patient = new Patient(LocalDate.parse("1968-01-01"),"M");
        patient.setPerson(insertedPerson);

        Patient insertedPatient = patientManager.create(patient);


        assertNotNull(insertedEntity);
        assertEquals(3, insertedEntity.getId());
        assertNotNull(insertedEntity.getCreatedDate());

        assertNotNull(insertedPerson);
        assertEquals(3, insertedPerson.getId());

        assertNotNull(insertedPatient);
        assertEquals("John", insertedPatient.getPerson().getFirstName());
        assertEquals("Smith", insertedPatient.getPerson().getLastName());
        assertNotNull(insertedEntity.getCreatedDate());
    }
*/

    @Test
    void testCreatePatient() {

        LocalDate birthDate = LocalDate.parse("1968-01-01");
        String gender = "M";
        String firstName = "John";
        String lastName = "Smith";
        PersonType personType = personTypeManager.get(3);

        Patient patient = new Patient(personType, firstName, lastName, birthDate, gender);
        Patient insertedPatient = patientManager.create(patient);

        assertNotNull(insertedPatient);
        assertEquals("John", insertedPatient.getFirstName());
        assertEquals("Smith", insertedPatient.getLastName());
        assertNotNull(insertedPatient.getCreatedDate());
    }


    /**
     * Test Load user account.
     */
/*
    @Test
    void testGetPatientProfile() {

        String userName = "yesper";
        Profile profile = patientManager.getPatientProfile(userName);


*/
/*
        Person person = personManager.get(1);
        Address address = addressManager.get(1);

        Person personProfile = profile.getPerson();
        Address addressProfile = profile.getAddress();


        assertEquals(person, personProfile);
        assertEquals(address, addressProfile);
*//*


    }
*/

}