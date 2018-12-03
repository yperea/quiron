package co.net.quiron.persistence.schedule;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.schedule.Shift;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test out The Shifts repository.
 */
class ShiftRepositoryTest {

    IAppRepository<Shift> shiftRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        shiftRepository = RepositoryFactory.getDBContext(Shift.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting Shift by its ID.
     */
    @Test
    void testGetShiftById() {
        Shift shift = shiftRepository.get(1);
        String shiftName = shift.getName();
        assertEquals("Weekday Morning Spring 2018", shiftName);
        //assertEquals("???", shift.getShiftSchedules().stream().findFirst().get());
    }

    /**
     * Test the get all shifts.
     */
    @Test
    void testGetAllShifts() {
        List<Shift> shiftList = shiftRepository.getList();
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
        Shift insertedShift = shiftRepository.create(newShift);

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

        Shift shiftToUpdate = shiftRepository.get(shiftId);
        shiftToUpdate.setName(newShiftName);

        shiftRepository.update(shiftToUpdate);
        Shift shiftUpdated = shiftRepository.get(shiftId);

        assertEquals(shiftToUpdate, shiftUpdated);
    }

    /**
     * Test the shift deletion when has a foreign key constraint.
     */
    @Test
    void testDeleteShiftWithConstrint() {

        int shiftIdToDelete = 1;

        //assertNull(shiftRepository.get(shiftIdToDelete));

        Throwable exception = assertThrows(PersistenceException.class,
                () -> shiftRepository.delete(shiftRepository.get(shiftIdToDelete)));
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
        Shift insertedShift = shiftRepository.create(newShift);

        shiftRepository.delete(shiftRepository.get(insertedShift.getId()));
        assertNull(shiftRepository.get(insertedShift.getId()));
    }
}