package co.net.quiron.persistence.care;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TreatmentRepositoryTest {
    IAppRepository<Treatment> treatmentRepository;
    IAppRepository<Prescription> prescriptionRepository;
    IAppRepository<Visit> visitRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        treatmentRepository = RepositoryFactory.getDBContext(Treatment.class);
        prescriptionRepository = RepositoryFactory.getDBContext(Prescription.class);
        visitRepository = RepositoryFactory.getDBContext(Visit.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get treatment by id.
     */
    @Test
    void testGetTreatmentById() {
        Treatment treatment = treatmentRepository.get(1);
        String comments = treatment.getPatientComments();
        assertEquals("I feel better now", comments);
    }

    /**
     * Test the get all treatments.
     */
    @Test
    void testGetAllTreatments() {
        List<Treatment> treatmentList = treatmentRepository.getList();
        assertEquals(2, treatmentList.size());
    }

    /**
     * Test the get all treatments by property.
     */
    @Test
    void testGetTreatmentsEquals() {

        String status = "C";
        List<Treatment> treatmentList = treatmentRepository.getListEquals("status", status);
        assertEquals(2, treatmentList.size());
    }

    /**
     * Test the treatment creation.
     */
    @Test
    void testCreateTreatment() {

        int symptomId = 1;
        String symptomName = "Sore throat";
        int diagnosticId = 1;
        String diagnosticName = "Diagnsotic 1";
        LocalDate startDate = LocalDate.parse("2018-02-01");
        LocalDate endDate = LocalDate.parse("2018-02-08");
        String patientComments = "";
        String providerComments = "Follow instructions";
        String status = "A";
        int evaluation = 0;

        Visit visit = visitRepository.get(3);

        Treatment newTreatment = new Treatment(visit);
        newTreatment.setStartDate(startDate);
        newTreatment.setEndDate(endDate);
        newTreatment.setPatientComments(patientComments);
        newTreatment.setProviderComments(providerComments);
        newTreatment.setStatus(status);
        newTreatment.setEvaluation(evaluation);

        Treatment insertedTreatment = treatmentRepository.create(newTreatment);

        assertNotNull(insertedTreatment);
        assertEquals(status, insertedTreatment.getStatus());
        assertEquals(providerComments, insertedTreatment.getProviderComments());
    }

    /**
     * Test the treatment update.
     */
    @Test
    void testUpdateTreatment() {

        int treatmentId = 1;
        int newEvaluation = 3;
        String newStatus = "D";
        String newSymptomName = "Symptom 20";

        Treatment treatmentToUpdate = treatmentRepository.get(treatmentId);
        treatmentToUpdate.setStatus(newStatus);
        treatmentToUpdate.setEvaluation(newEvaluation);

        treatmentRepository.update(treatmentToUpdate);
        Treatment treatmentUpdated = treatmentRepository.get(treatmentId);

        assertEquals(newEvaluation, treatmentUpdated.getEvaluation());
        assertEquals(newStatus, treatmentUpdated.getStatus());
    }

    /**
     * Test the treatment deletion.
     */
    @Test
    void testDeleteTreatment() {

        int treatmentIdToDelete = 3;

/*
        int symptomId = 1;
        String symptomName = "Sore throat";
        int diagnosticId = 1;
        String diagnosticName = "Diagnsotic 1";
*/

        Visit visit = visitRepository.get(3);

        Treatment newTreatment = new Treatment(visit);
        Treatment insertedTreatment = treatmentRepository.create(newTreatment);

        treatmentRepository.delete(treatmentRepository.get(treatmentIdToDelete));
        assertNull(treatmentRepository.get(treatmentIdToDelete));
    }

    /**
     * Test create treatment with one prescription.
     */
    @Test
    void testCreateTreatmentWithOnePrescription() {

        int prescriptionId = 2;
        String intructions = "100 mg every 12 hours, not to exceed 200 mg in 24 hours.";

/*
        int symptomId = 1;
        String symptomName = "Sore throat";
        int diagnosticId = 1;
        String diagnosticName = "Diagnsotic 1";
*/
        LocalDate startDate = LocalDate.parse("2018-02-01");
        LocalDate endDate = LocalDate.parse("2018-02-08");
        String patientComments = "";
        String providerComments = "Follow instructions";
        String status = "A";
        int evaluation = 0;

        Visit visit = visitRepository.get(3);

        Treatment newTreatment = new Treatment(visit);
        newTreatment.setStartDate(startDate);
        newTreatment.setEndDate(endDate);
        newTreatment.setPatientComments(patientComments);
        newTreatment.setProviderComments(providerComments);
        newTreatment.setStatus(status);
        newTreatment.setEvaluation(evaluation);


        Prescription prescription = prescriptionRepository.get(prescriptionId);
        newTreatment.addPrescription(prescription);

        Treatment insertedTreatment = treatmentRepository.create(newTreatment);

        int prescriptionsAssigned = insertedTreatment.getPrescriptions().size();
        Prescription newPrescription = insertedTreatment.getPrescriptions().stream().findFirst().get();

        assertNotNull(insertedTreatment);
/*
        assertEquals(symptomName, insertedTreatment.getSymptomName());
        assertEquals(diagnosticName, insertedTreatment.getDiagnosticName());
*/
        assertEquals(1,prescriptionsAssigned);
        assertEquals(intructions, newPrescription.getInstructions());
    }
}
