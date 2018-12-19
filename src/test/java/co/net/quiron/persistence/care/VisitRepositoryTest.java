package co.net.quiron.persistence.care;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Service;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VisitRepositoryTest {

    IAppRepository<Visit> visitRepository;

    IAppRepository<Patient> patientRepository;
    IAppRepository<Service> serviceRepository;
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

        serviceRepository = RepositoryFactory.getDBContext(Service.class);
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

    /**
     * Test the Medication update.
     */
    @Test
    void testUpdateVisit() {

        String startDate = "12/9/2018 13:30";
        int visitId = 7;

        LocalDateTime actualStartDate = LocalDateTime.parse(startDate,
                DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm"));

        Visit visitToUpdate = visitRepository.get(visitId);
        visitToUpdate.setActualStartDate(actualStartDate);

        visitRepository.update(visitToUpdate);
        Visit visitUpdated = visitRepository.get(visitId);

        assertEquals(actualStartDate, visitUpdated.getActualStartDate().plusHours(6));
    }


    @Test
    void testCreateVisit() {

        String status = "A";
        Map<String, Integer> shiftScheduleId = new TreeMap<>();
        shiftScheduleId.put("shift", 12);
        shiftScheduleId.put("weekDay", 6);

        ShiftSchedule shiftSchedule = shiftScheduleRepository.get(shiftScheduleId);
        Provider provider = providerRepository.get(4);
        Organization organization = organizationRepository.get(1);
        Address location = locationRepository.get(5);
        ProviderSchedule providerSchedule = new ProviderSchedule(provider, shiftSchedule, location, organization);
        providerScheduleRepository.update(providerSchedule);

        Patient patient = patientRepository.get(5);
        Service service = serviceRepository.get(1);

        Visit visit = new Visit();
        visit.setProviderSchedule(providerSchedule);
        visit.setPatient(patient);
        visit.setService(service);
        visit.setStatus(status);

        Visit newVisit = visitRepository.create(visit);
        assertNotNull(newVisit);
    }

}
