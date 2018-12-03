package co.net.quiron.persistence.care;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Medication;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PrescriptionRepositoryTest {
    IAppRepository<Prescription> prescriptionRepository;
    IAppRepository<Medication> medicationRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        prescriptionRepository = RepositoryFactory.getDBContext(Prescription.class);
        medicationRepository = RepositoryFactory.getDBContext(Medication.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get prescription by id.
     */
    @Test
    void testGetPrescriptionById() {
        Prescription prescription = prescriptionRepository.get(1);
        String instructions = prescription.getInstructions();
        assertEquals("10 mL every 12 hours, not to exceed 20 mL in 24 hours.", instructions);
    }

    /**
     * Test the get all prescriptions.
     */
    @Test
    void testGetAllPrescriptions() {
        List<Prescription> prescriptionList = prescriptionRepository.getList();
        assertEquals(2, prescriptionList.size());
    }

    /**
     * Test the prescription creation.
     */
    @Test
    void testCreatePrescription() {

        String instructions = "One pill every hour";

        Medication medication = medicationRepository.get(1);

        Prescription newPrescription = new Prescription(instructions, medication);
        Prescription insertedPrescription = prescriptionRepository.create(newPrescription);

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

        Prescription prescriptionToUpdate = prescriptionRepository.get(prescriptionId);
        prescriptionToUpdate.setInstructions(newInstructions);
        prescriptionRepository.update(prescriptionToUpdate);
        Prescription prescriptionUpdated = prescriptionRepository.get(prescriptionId);

        assertEquals(newInstructions, prescriptionUpdated.getInstructions());
    }

    /**
     * Test the prescription deletion.
     */
    @Test
    void testDeletePrescription() {

        int prescriptionIdToDelete = 3;

        String instructions = "One pill every hour";

        Medication medication = medicationRepository.get(1);

        Prescription newPrescription = new Prescription(instructions, medication);
        Prescription insertedPrescription = prescriptionRepository.create(newPrescription);

        prescriptionRepository.delete(prescriptionRepository.get(prescriptionIdToDelete));
        assertNull(prescriptionRepository.get(prescriptionIdToDelete));
    }
}
