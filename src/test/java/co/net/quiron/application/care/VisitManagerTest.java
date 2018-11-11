package co.net.quiron.application.care;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.domain.schedule.ProviderSchedule;
import co.net.quiron.domain.schedule.ShiftSchedule;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitManagerTest {

    EntityManager<Visit> visitManager;

    EntityManager<Patient> patientManager;
    EntityManager<ProviderSchedule> providerScheduleManager;
    EntityManager<ShiftSchedule> shiftScheduleManager;
    EntityManager<Provider> providerManager;
    EntityManager<Organization> organizationManager;
    EntityManager<Address> locationManager;
    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        visitManager = ManagerFactory.getManager(Visit.class);
        patientManager = ManagerFactory.getManager(Patient.class);

        providerScheduleManager = ManagerFactory.getManager(ProviderSchedule.class);
        shiftScheduleManager = ManagerFactory.getManager(ShiftSchedule.class);
        providerManager = ManagerFactory.getManager(Provider.class);
        organizationManager = ManagerFactory.getManager(Organization.class);
        locationManager = ManagerFactory.getManager(Address.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }


    /**
     * Test getting Shift by its ID.
     */
    @Test
    void testGetVisitById() {

        Visit visit = visitManager.get(1);
        assertEquals("Yesid", visit.getProviderSchedule().getProvider().getFirstName());

    }

    /**
     * Test the get all visits.
     */
    @Test
    void testGetAllVisits() {
        List<Visit> visitList = visitManager.getList();
        assertEquals(9, visitList.size());
    }

    /**
     * Test the get visits by patient.
     */
    @Test
    void testGetVisitsByPatient() {

        Patient patient = patientManager.get(5);
        Provider provider = providerManager.get(3);

        Map<String, Object> params = new TreeMap<>();
        params.put("patient", patient);

        List<Visit> myVisitList = visitManager.getListEquals(params);
        assertEquals(9, myVisitList.size());
        assertEquals(4, myVisitList
                                .stream()
                                .filter(ps -> ps.getProviderSchedule()
                                        .getProvider()
                                        .equals(provider))
                                .collect(Collectors.toList())
                                .size());

    }
}
