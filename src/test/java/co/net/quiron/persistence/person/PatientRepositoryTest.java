package co.net.quiron.persistence.person;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.*;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Patient repository tester.
 */
class PatientRepositoryTest {

    IAppRepository<Patient> patientRepository;
    IAppRepository<Address> addressRepository;
    IAppRepository<PersonType> personTypeRepository;
    IAppRepository<Organization> organizationRepository;

    /**
     * Initializes repositorys and data for the test.
     */
    @BeforeEach
    void setUp() {
        patientRepository = RepositoryFactory.getDBContext(Patient.class);
        addressRepository = RepositoryFactory.getDBContext(Address.class);
        personTypeRepository = RepositoryFactory.getDBContext(PersonType.class);
        organizationRepository = RepositoryFactory.getDBContext(Organization.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Tests get patient by id.
     */
    @Test
    void testGetPatientById() {
        Patient patient = patientRepository.get(4);
        String firstName = patient.getFirstName();
        assertEquals("Claudia", firstName);
    }


    /**
     * Tests get all patients.
     */
    @Test
    void testGetAllPatients() {
        List<Patient> patientList = patientRepository.getList();
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
        PersonType personType = personTypeRepository.get(3);
        Organization organization = organizationRepository.get(1);

        Patient patient = new Patient(personType, firstName, lastName, birthDate, gender);
        patient.setOrganization(organization);
        patient.setSubscriberCode(subscriberCode);
        Patient insertedPatient = patientRepository.create(patient);

        assertNotNull(insertedPatient);
        assertEquals("John", insertedPatient.getFirstName());
        assertEquals("Smith", insertedPatient.getLastName());
        assertEquals("123465", insertedPatient.getSubscriberCode());
        assertEquals("Group Health Cooperative", insertedPatient.getOrganization().getName());
        assertNotNull(insertedPatient.getCreatedDate());
    }
}