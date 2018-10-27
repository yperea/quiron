package co.net.quiron.application.patient;

import co.net.quiron.application.person.BusinessEntityManager;
import co.net.quiron.application.person.PersonManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
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

    EntityManager<BusinessEntity> businessEntityManager;
    EntityManager<Person> personManager;
    EntityManager<Patient> patientManager;

    /**
     * Initializes managers and data for the test.
     */
    @BeforeEach
    void setUp() {

        businessEntityManager = new BusinessEntityManager();
        personManager = new PersonManager();
        patientManager = new PatientManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");

    }

    /**
     * Tests get patient by id.
     */
    @Test
    void testGetPatientById() {
        Patient patient = patientManager.get(1);
        String firstName = patient.getPerson().getFirstName();
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
}