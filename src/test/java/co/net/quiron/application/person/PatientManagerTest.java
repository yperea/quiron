package co.net.quiron.application.person;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
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

    /**
     * The Patient manager.
     */
    EntityManager<Patient> patientManager;
    /**
     * The Address manager.
     */
    EntityManager<Address> addressManager;
    /**
     * The Person type manager.
     */
    EntityManager<PersonType> personTypeManager;
    /**
     * The Organization manager.
     */
    EntityManager<Organization> organizationManager;

    /**
     * Initializes managers and data for the test.
     */
    @BeforeEach
    void setUp() {
        patientManager = ManagerFactory.getManager(Patient.class);
        addressManager = ManagerFactory.getManager(Address.class);
        personTypeManager = ManagerFactory.getManager(PersonType.class);
        organizationManager = ManagerFactory.getManager(Organization.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Tests get patient by id.
     */
    @Test
    void testGetPatientById() {
        Patient patient = patientManager.get(4);
        String firstName = patient.getFirstName();
        assertEquals("Claudia", firstName);
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
     * Test create patient.
     */
    @Test
    void testCreatePatient() {

        LocalDate birthDate = LocalDate.parse("1968-01-01");
        String gender = "M";
        String firstName = "John";
        String lastName = "Smith";
        String subscriberCode = "123465";
        PersonType personType = personTypeManager.get(3);
        Organization organization = organizationManager.get(1);

        Patient patient = new Patient(personType, firstName, lastName, birthDate, gender);
        patient.setOrganization(organization);
        patient.setSubscriberCode(subscriberCode);
        Patient insertedPatient = patientManager.create(patient);

        assertNotNull(insertedPatient);
        assertEquals("John", insertedPatient.getFirstName());
        assertEquals("Smith", insertedPatient.getLastName());
        assertEquals("123465", insertedPatient.getSubscriberCode());
        assertEquals("Group Health Cooperative", insertedPatient.getOrganization().getName());
        assertNotNull(insertedPatient.getCreatedDate());
    }
}