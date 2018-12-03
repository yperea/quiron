package co.net.quiron.persistence.care;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.domain.schedule.ProviderSchedule;
import co.net.quiron.domain.schedule.ShiftSchedule;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitRepositoryTest {

    IAppRepository<Visit> visitRepository;

    IAppRepository<Patient> patientRepository;
    IAppRepository<ProviderSchedule> providerScheduleRepository;
    IAppRepository<ShiftSchedule> shiftScheduleRepository;
    IAppRepository<Provider> providerRepository;
    IAppRepository<Organization> organizationRepository;
    IAppRepository<Address> locationRepository;
    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        visitRepository = RepositoryFactory.getDBContext(Visit.class);
        patientRepository = RepositoryFactory.getDBContext(Patient.class);

        providerScheduleRepository = RepositoryFactory.getDBContext(ProviderSchedule.class);
        shiftScheduleRepository = RepositoryFactory.getDBContext(ShiftSchedule.class);
        providerRepository = RepositoryFactory.getDBContext(Provider.class);
        organizationRepository = RepositoryFactory.getDBContext(Organization.class);
        locationRepository = RepositoryFactory.getDBContext(Address.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }


    /**
     * Test getting Shift by its ID.
     */
    @Test
    void testGetVisitById() {

        Visit visit = visitRepository.get(1);
        assertEquals("Yesid", visit.getProviderSchedule().getProvider().getFirstName());

    }

    /**
     * Test the get all visits.
     */
    @Test
    void testGetAllVisits() {
        List<Visit> visitList = visitRepository.getList();
        assertEquals(9, visitList.size());
    }

    /**
     * Test the get visits by patient.
     */
    @Test
    void testGetVisitsByPatient() {

        Patient patient = patientRepository.get(5);
        Provider provider = providerRepository.get(3);

        Map<String, Object> params = new TreeMap<>();
        params.put("patient", patient);

        List<Visit> myVisitList = visitRepository.getListEquals(params);
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
