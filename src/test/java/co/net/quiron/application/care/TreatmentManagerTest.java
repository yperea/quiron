package co.net.quiron.application.care;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TreatmentManagerTest {
    EntityManager<Treatment> treatmentManager;
    EntityManager<Prescription> prescriptionManager;
    EntityManager<Visit> visitManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        treatmentManager = ManagerFactory.getManager(Treatment.class);
        prescriptionManager = ManagerFactory.getManager(Prescription.class);
        visitManager = ManagerFactory.getManager(Visit.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get treatment by id.
     */
    @Test
    void testGetTreatmentById() {
        Treatment treatment = treatmentManager.get(1);
        String symptom = treatment.getSymptomName();
        assertEquals("Sore throat", symptom);
    }

    /**
     * Test the get all treatments.
     */
    @Test
    void testGetAllTreatments() {
        List<Treatment> treatmentList = treatmentManager.getList();
        assertEquals(2, treatmentList.size());
    }

    /**
     * Test the get all treatments by property.
     */
    @Test
    void testGetTreatmentsEquals() {

        String status = "C";
        List<Treatment> treatmentList = treatmentManager.getListEquals("status", status);
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

        Visit visit = visitManager.get(3);

        Treatment newTreatment = new Treatment(visit, symptomId, symptomName, diagnosticId, diagnosticName);
        newTreatment.setStartDate(startDate);
        newTreatment.setEndDate(endDate);
        newTreatment.setPatientComments(patientComments);
        newTreatment.setProviderComments(providerComments);
        newTreatment.setStatus(status);
        newTreatment.setEvaluation(evaluation);

        Treatment insertedTreatment = treatmentManager.create(newTreatment);

        assertNotNull(insertedTreatment);
        assertEquals(symptomName, insertedTreatment.getSymptomName());
        assertEquals(diagnosticName, insertedTreatment.getDiagnosticName());
    }

    /**
     * Test the treatment update.
     */
    @Test
    void testUpdateTreatment() {

        int treatmentId = 1;
        int newSymptomId = 20;
        String newSymptomName = "Symptom 20";

        Treatment treatmentToUpdate = treatmentManager.get(treatmentId);
        treatmentToUpdate.setSymptomId(newSymptomId);
        treatmentToUpdate.setSymptomName(newSymptomName);

        treatmentManager.update(treatmentToUpdate);
        Treatment treatmentUpdated = treatmentManager.get(treatmentId);

        assertEquals(newSymptomName, treatmentUpdated.getSymptomName());
    }

    /**
     * Test the treatment deletion.
     */
    @Test
    void testDeleteTreatment() {

        int treatmentIdToDelete = 3;

        int symptomId = 1;
        String symptomName = "Sore throat";
        int diagnosticId = 1;
        String diagnosticName = "Diagnsotic 1";

        Visit visit = visitManager.get(3);

        Treatment newTreatment = new Treatment(visit, symptomId, symptomName, diagnosticId, diagnosticName);
        Treatment insertedTreatment = treatmentManager.create(newTreatment);

        treatmentManager.delete(treatmentManager.get(treatmentIdToDelete));
        assertNull(treatmentManager.get(treatmentIdToDelete));
    }

    /**
     * Test create treatment with one prescription.
     */
    @Test
    void testCreateTreatmentWithOnePrescription() {

        int prescriptionId = 2;
        String intructions = "100 mg every 12 hours, not to exceed 200 mg in 24 hours.";

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

        Visit visit = visitManager.get(3);

        Treatment newTreatment = new Treatment(visit, symptomId, symptomName, diagnosticId, diagnosticName);
        newTreatment.setStartDate(startDate);
        newTreatment.setEndDate(endDate);
        newTreatment.setPatientComments(patientComments);
        newTreatment.setProviderComments(providerComments);
        newTreatment.setStatus(status);
        newTreatment.setEvaluation(evaluation);


        Prescription prescription = prescriptionManager.get(prescriptionId);
        newTreatment.addPrescription(prescription);

        Treatment insertedTreatment = treatmentManager.create(newTreatment);

        int prescriptionsAssigned = insertedTreatment.getPrescriptions().size();
        Prescription newPrescription = insertedTreatment.getPrescriptions().stream().findFirst().get();

        assertNotNull(insertedTreatment);
        assertEquals(symptomName, insertedTreatment.getSymptomName());
        assertEquals(diagnosticName, insertedTreatment.getDiagnosticName());
        assertEquals(1,prescriptionsAssigned);
        assertEquals(intructions, newPrescription.getInstructions());
    }
}
