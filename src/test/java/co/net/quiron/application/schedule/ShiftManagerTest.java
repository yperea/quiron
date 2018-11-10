package co.net.quiron.application.schedule;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.schedule.Shift;
import co.net.quiron.test.util.DatabaseManager;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.accessibility.AccessibleStateSet;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test out The Shifts manager.
 */
class ShiftManagerTest {

    EntityManager<Shift> shiftManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        shiftManager = ManagerFactory.getManager(Shift.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting Shift by its ID.
     */
    @Test
    void testGetShiftById() {
        Shift shift = shiftManager.get(1);
        String shiftName = shift.getName();
        assertEquals("Weekday Morning Spring 2018", shiftName);
        //assertEquals("???", shift.getShiftSchedules().stream().findFirst().get());
    }

    /**
     * Test the get all shifts.
     */
    @Test
    void testGetAllShifts() {
        List<Shift> shiftList = shiftManager.getList();
        assertEquals(12, shiftList.size());
    }


    /**
     * Test the shift creation.
     */
    @Test
    void testCreateShift() {

        String shiftName = "Weekday Morning Spring 2019";
        String shiftDescription = "Weekday Morning Spring 2019";
        LocalDate shiftStartDate = LocalDate.parse("2019-03-01");
        LocalDate shiftEndDate = LocalDate.parse("2019-05-31");
        String shiftStatus = "A";

        Shift newShift = new Shift(shiftName, shiftDescription, shiftStartDate, shiftEndDate, shiftStatus);
        Shift insertedShift = shiftManager.create(newShift);

        assertNotNull(insertedShift);
        assertEquals("Weekday Morning Spring 2019", insertedShift.getName());
        assertNotNull(insertedShift.getCreatedDate());
    }

    /**
     * Test the shift update.
     */
    @Test
    void testUpdateShift() {

        int shiftId = 1;
        String newShiftName = "Weekday Morning Spring 2019";

        Shift shiftToUpdate = shiftManager.get(shiftId);
        shiftToUpdate.setName(newShiftName);

        shiftManager.update(shiftToUpdate);
        Shift shiftUpdated = shiftManager.get(shiftId);

        assertEquals(shiftToUpdate, shiftUpdated);
    }

    /**
     * Test the shift deletion when has a foreign key constraint.
     */
    @Test
    void testDeleteShiftWithConstrint() {

        int shiftIdToDelete = 1;

        //assertNull(shiftManager.get(shiftIdToDelete));

        Throwable exception = assertThrows(PersistenceException.class,
                () -> shiftManager.delete(shiftManager.get(shiftIdToDelete)));
        assertEquals(PersistenceException.class, exception.getClass());
    }

    /**
     * Test the shift deletion.
     */
    @Test
    void testDeleteNewShift() {

        String shiftName = "Weekday Morning Spring 2019";
        String shiftDescription = "Weekday Morning Spring 2019";
        LocalDate shiftStartDate = LocalDate.parse("2019-03-01");
        LocalDate shiftEndDate = LocalDate.parse("2019-05-31");
        String shiftStatus = "A";

        Shift newShift = new Shift(shiftName, shiftDescription, shiftStartDate, shiftEndDate, shiftStatus);
        Shift insertedShift = shiftManager.create(newShift);

        shiftManager.delete(shiftManager.get(insertedShift.getId()));
        assertNull(shiftManager.get(insertedShift.getId()));
    }
}