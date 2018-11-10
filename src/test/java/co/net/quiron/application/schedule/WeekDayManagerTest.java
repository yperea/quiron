package co.net.quiron.application.schedule;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.schedule.WeekDay;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeekDayManagerTest {

    EntityManager<WeekDay> weekDayManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        weekDayManager = ManagerFactory.getManager(WeekDay.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting WeekDay by its ID.
     */
    @Test
    void testGetWeekDayById() {
        WeekDay weekDay = weekDayManager.get(5);
        String weekDayName = weekDay.getName();
        assertEquals("Friday", weekDayName);
        //assertEquals("???", weekDay.getWeekDaySchedules().stream().findFirst().get());
    }

    /**
     * Test the get all weekDays.
     */
    @Test
    void testGetAllWeekDays() {
        List<WeekDay> weekDayList = weekDayManager.getList();
        assertEquals(7, weekDayList.size());
    }

    /**
     * Test the weekDay creation.
     */
    @Test
    void testCreateWeekDay() {

        String weekDayCode = "L";
        String weekDayName = "Lunes";

        WeekDay newWeekDay = new WeekDay(weekDayCode, weekDayName);
        WeekDay insertedWeekDay = weekDayManager.create(newWeekDay);

        assertNotNull(insertedWeekDay);
        assertEquals(weekDayName, insertedWeekDay.getName());
        assertNotNull(insertedWeekDay.getCreatedDate());
    }

    /**
     * Test the weekDay update.
     */
    @Test
    void testUpdateWeekDay() {

        int weekDayId = 5;
        String newWeekDayName = "Viernes";

        WeekDay weekDayToUpdate = weekDayManager.get(weekDayId);
        weekDayToUpdate.setName(newWeekDayName);

        weekDayManager.update(weekDayToUpdate);
        WeekDay weekDayUpdated = weekDayManager.get(weekDayId);

        assertEquals(weekDayToUpdate, weekDayUpdated);
    }

    /**
     * Test the weekDay deletion when has a foreign key constraint.
     */
    @Test
    void testDeleteWeekDayWithConstraint() {

        int weekDayIdToDelete = 5;

        Throwable exception = assertThrows(PersistenceException.class,
                () -> weekDayManager.delete(weekDayManager.get(weekDayIdToDelete)));
        assertEquals(PersistenceException.class, exception.getClass());
    }

    /**
     * Test the weekDay deletion.
     */
    @Test
    void testDeleteNewWeekDay() {

        String weekDayCode = "L";
        String weekDayName = "Lunes";

        WeekDay newWeekDay = new WeekDay(weekDayCode, weekDayName);
        WeekDay insertedWeekDay = weekDayManager.create(newWeekDay);

        weekDayManager.delete(weekDayManager.get(insertedWeekDay.getId()));
        assertNull(weekDayManager.get(insertedWeekDay.getId()));
    }
}