package co.net.quiron.persistence.schedule;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.schedule.WeekDay;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.PersistenceException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WeekDayRepositoryTest {

    IAppRepository<WeekDay> weekDayRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        weekDayRepository = RepositoryFactory.getDBContext(WeekDay.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting WeekDay by its ID.
     */
    @Test
    void testGetWeekDayById() {
        WeekDay weekDay = weekDayRepository.get(5);
        String weekDayName = weekDay.getName();
        assertEquals("Friday", weekDayName);
        //assertEquals("???", weekDay.getWeekDaySchedules().stream().findFirst().get());
    }

    /**
     * Test the get all weekDays.
     */
    @Test
    void testGetAllWeekDays() {
        List<WeekDay> weekDayList = weekDayRepository.getList();
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
        WeekDay insertedWeekDay = weekDayRepository.create(newWeekDay);

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

        WeekDay weekDayToUpdate = weekDayRepository.get(weekDayId);
        weekDayToUpdate.setName(newWeekDayName);

        weekDayRepository.update(weekDayToUpdate);
        WeekDay weekDayUpdated = weekDayRepository.get(weekDayId);

        assertEquals(weekDayToUpdate, weekDayUpdated);
    }

    /**
     * Test the weekDay deletion when has a foreign key constraint.
     */
    @Test
    void testDeleteWeekDayWithConstraint() {

        int weekDayIdToDelete = 5;

        Throwable exception = assertThrows(PersistenceException.class,
                () -> weekDayRepository.delete(weekDayRepository.get(weekDayIdToDelete)));
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
        WeekDay insertedWeekDay = weekDayRepository.create(newWeekDay);

        weekDayRepository.delete(weekDayRepository.get(insertedWeekDay.getId()));
        assertNull(weekDayRepository.get(insertedWeekDay.getId()));
    }
}