package co.net.quiron.persistence.care;


import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Medication;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MedicationRepositoryTest {
    IAppRepository<Medication> medicationRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        medicationRepository = RepositoryFactory.getDBContext(Medication.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting Medication by its ID.
     */
    @Test
    void testGetMedicationById() {
//        Medication Medication = medicationRepository.getMedication(1);
        Medication Medication = medicationRepository.get(1);
        assertEquals("Robitussin", Medication.getName());
    }


    /**
     * Test the get all Medications.
     */
    @Test
    void testGetAllMedications() {
        List<Medication> MedicationList = medicationRepository.getList();
        assertEquals(4, MedicationList.size());
    }


    /**
     * Test the Medication creation.
     */
    @Test
    void testCreateMedication() {

        String newMedicationName = "Dolex";
        Medication newMedication = new Medication(newMedicationName);
        Medication insertedMedication = medicationRepository.create(newMedication);

        assertNotNull(insertedMedication);
        assertEquals("Dolex", insertedMedication.getName());
        assertNotNull(insertedMedication.getCreatedDate());
    }

    /**
     * Test the Medication update.
     */
    @Test
    void testUpdateMedication() {

        String newMedicationName = "Dolex";
        int MedicationId = 2;

        Medication MedicationToUpdate = medicationRepository.get(MedicationId);
        MedicationToUpdate.setName(newMedicationName);

        medicationRepository.update(MedicationToUpdate);
        Medication MedicationUpdated = medicationRepository.get(MedicationId);

        assertEquals(newMedicationName, MedicationUpdated.getName());
    }

    /**
     * Test the Medication deletion.
     */
    @Test
    void testDeleteMedication() {

        int medicationIdToDelete = 5;
        String newMedicationName = "Dolex";
        Medication newMedication = new Medication(newMedicationName);
        Medication insertedMedication = medicationRepository.create(newMedication);

        medicationRepository.delete(medicationRepository.get(medicationIdToDelete));
        assertNull(medicationRepository.get(medicationIdToDelete));
    }
}