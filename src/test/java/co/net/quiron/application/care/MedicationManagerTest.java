package co.net.quiron.application.care;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Medication;
import co.net.quiron.domain.location.AddressType;
import co.net.quiron.domain.location.State;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MedicationManagerTest {
    EntityManager<Medication> medicationManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        medicationManager = ManagerFactory.getManager(Medication.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting Medication by its ID.
     */
    @Test
    void testGetMedicationById() {
//        Medication Medication = medicationManager.getMedication(1);
        Medication Medication = medicationManager.get(1);
        assertEquals("Robitussin", Medication.getName());
    }


    /**
     * Test the get all Medications.
     */
    @Test
    void testGetAllMedications() {
        List<Medication> MedicationList = medicationManager.getList();
        assertEquals(2, MedicationList.size());
    }


    /**
     * Test the Medication creation.
     */
    @Test
    void testCreateMedication() {

        String newMedicationName = "Dolex";
        Medication newMedication = new Medication(newMedicationName);
        Medication insertedMedication = medicationManager.create(newMedication);

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

        Medication MedicationToUpdate = medicationManager.get(MedicationId);
        MedicationToUpdate.setName(newMedicationName);

        medicationManager.update(MedicationToUpdate);
        Medication MedicationUpdated = medicationManager.get(MedicationId);

        assertEquals(newMedicationName, MedicationUpdated.getName());
    }

    /**
     * Test the Medication deletion.
     */
    @Test
    void testDeleteMedication() {

        int medicationIdToDelete = 3;
        String newMedicationName = "Dolex";
        Medication newMedication = new Medication(newMedicationName);
        Medication insertedMedication = medicationManager.create(newMedication);

        medicationManager.delete(medicationManager.get(medicationIdToDelete));
        assertNull(medicationManager.get(medicationIdToDelete));
    }
}