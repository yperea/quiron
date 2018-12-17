package co.net.quiron.application.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import co.net.quiron.util.FormManager;
import org.hibernate.boot.model.source.internal.hbm.AbstractSingularAttributeSourceEmbeddedImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Visit manager test.
 */
class VisitManagerTest {

    AccountManager accountManager;
    VisitManager visitManager;
    IAppRepository<Patient> patientRepository;
    IAppRepository<Provider> providerRepository;
    IAppRepository<Visit> visitRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        String username = "yesper";
        String personType = "provider";
        accountManager = new AccountManager(username, personType);
        visitManager = new VisitManager(accountManager);
        patientRepository = RepositoryFactory.getDBContext(Patient.class);
        providerRepository = RepositoryFactory.getDBContext(Provider.class);;
        visitRepository = RepositoryFactory.getDBContext(Visit.class);;

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get patient visit.
     */
    @Test
    void testGetPatientVisit() {
        int id = 1;
        int patientId =5;
        int providerId = 3;
        String service = "Therapy";
        VisitManager visitManager = new VisitManager(accountManager);
        Visit visit = visitManager.getPatientVisit(id);
        Patient patient = patientRepository.get(patientId);
        Provider provider = providerRepository.get(providerId);

        assertNotNull(visit);
        assertEquals(service, visit.getService().getName());
        assertEquals(patient, visit.getPatient());
        assertEquals(provider, visit.getProviderSchedule().getProvider());
    }

    /**
     * Test save visit.
     */
    @Test
    void testSaveVisit() {
        int id = 1;
        Visit visit = visitManager.getPatientVisit(id);

        String visitStartDate = "12/19/2018";
        String visitStartTime = "13:00";
        String visitEndTime = "14:00";
        int symptomId = 10;
        String symptomName = "Diez";
        int diagnosisId = 4;
        String diagnosisName = "Cuatro";
        String statusCode = "C";
        double weight = 120;
        double height = 4.5;
        double pulse = 60;
        double respiration = 15;
        double bmi = 30;
        double temperature = 70;
        String providerComments = "Take care";
        String actualStartDate = visitStartDate + " " + visitStartTime;
        String actualEndDate = visitStartDate + " " + visitEndTime;

        visit.setStatus(statusCode);
        visit.setSymptomId(symptomId);
        visit.setSymptomName(symptomName);
        visit.setDiagnosticId(diagnosisId);
        visit.setDiagnosticName(diagnosisName);
        visit.setPatientWeight(weight);
        visit.setPatientHeight(height);
        visit.setPatientPulse(pulse);
        visit.setPatientRespiration(respiration);
        visit.setPatientBMI(bmi);
        visit.setPatientTemperature(temperature);
        visit.setProviderComments(providerComments);

        if(!visitStartTime.equals("")) {
            visit.setActualStartDate(LocalDateTime.parse(actualStartDate,
                    DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm")));
        }
        if(!visitEndTime.equals("")) {
            visit.setActualEndDate(LocalDateTime.parse(actualEndDate,
                    DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm")));
        }

        boolean visitSaved = visitManager.saveVisit(visit);

        assertEquals(true, visitSaved);

    }

    /**
     * Test add treatment.
     */
    @Test
    void testAddTreatment() {
        int id = 1;
        int medicationId = 1;

        String prescriptionStartDate = "12/19/2018";
        String prescriptionEndDate = "12/24/2018";
        String treatmentInstructions = "One spoon every night";

        Visit visit = visitManager.getPatientVisit(id);

        LocalDate treatmentStartDate = LocalDate.parse(prescriptionStartDate,
                DateTimeFormatter.ofPattern("MM/d/yyyy"));

        LocalDate treatmentEndDate = LocalDate.parse(prescriptionEndDate,
                DateTimeFormatter.ofPattern("MM/d/yyyy"));

        boolean treatmentAdded = visitManager.addTreatment(visit.getId(), medicationId,
                treatmentStartDate, treatmentEndDate, treatmentInstructions);

        assertEquals(true, treatmentAdded);

    }

    /**
     * Test get upcoming visits list.
     */
    @Test
    void testGetUpcomingVisitsList() {
        List<Visit> visits = visitManager.getVisitsList("A");
        assertEquals(1, visits.size());
    }

    /**
     * Test delete visit.
     */
    @Test
    void testDeleteVisit() {
        int id = 9;
        Visit visit = visitManager.getPatientVisit(id);
        visitManager.deleteVisit(visit);
        assertNull(visitManager.getPatientVisit(id));
    }
}