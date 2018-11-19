package co.net.quiron.application.care;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Medication;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PrescriptionManagerTest {
    EntityManager<Prescription> prescriptionManager;
    EntityManager<Medication> medicationManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        prescriptionManager = ManagerFactory.getManager(Prescription.class);
        medicationManager = ManagerFactory.getManager(Medication.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get prescription by id.
     */
    @Test
    void testGetPrescriptionById() {
        Prescription prescription = prescriptionManager.get(1);
        String instructions = prescription.getInstructions();
        assertEquals("10 mL every 12 hours, not to exceed 20 mL in 24 hours.", instructions);
    }

    /**
     * Test the get all prescriptions.
     */
    @Test
    void testGetAllPrescriptions() {
        List<Prescription> prescriptionList = prescriptionManager.getList();
        assertEquals(2, prescriptionList.size());
    }

    /**
     * Test the prescription creation.
     */
    @Test
    void testCreatePrescription() {

        String instructions = "One pill every hour";

        Medication medication = medicationManager.get(1);

        Prescription newPrescription = new Prescription(instructions, medication);
        Prescription insertedPrescription = prescriptionManager.create(newPrescription);

        assertNotNull(insertedPrescription);
        assertEquals(instructions, insertedPrescription.getInstructions());
    }

    /**
     * Test the prescription update.
     */
    @Test
    void testUpdatePrescription() {

        int prescriptionId = 1;
        String newInstructions = "One pill every hour";

        Prescription prescriptionToUpdate = prescriptionManager.get(prescriptionId);
        prescriptionToUpdate.setInstructions(newInstructions);
        prescriptionManager.update(prescriptionToUpdate);
        Prescription prescriptionUpdated = prescriptionManager.get(prescriptionId);

        assertEquals(newInstructions, prescriptionUpdated.getInstructions());
    }

    /**
     * Test the prescription deletion.
     */
    @Test
    void testDeletePrescription() {

        int prescriptionIdToDelete = 3;

        String instructions = "One pill every hour";

        Medication medication = medicationManager.get(1);

        Prescription newPrescription = new Prescription(instructions, medication);
        Prescription insertedPrescription = prescriptionManager.create(newPrescription);

        prescriptionManager.delete(prescriptionManager.get(prescriptionIdToDelete));
        assertNull(prescriptionManager.get(prescriptionIdToDelete));
    }
}
