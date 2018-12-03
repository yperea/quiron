package co.net.quiron.persistence.schedule;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.schedule.ShiftSchedule;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftScheduleRepositoryTest {

    IAppRepository<ShiftSchedule> shiftScheduleRepository;
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        shiftScheduleRepository = RepositoryFactory.getDBContext(ShiftSchedule.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting Shift by its ID.
     */
    @Test
    void testGetShiftById() {

        Map<String, Integer> shiftScheduleId = new TreeMap<>();
        shiftScheduleId.put("shift", 11);
        shiftScheduleId.put("weekDay", 5);

        ShiftSchedule shiftSchedule = shiftScheduleRepository.get(shiftScheduleId);
        LocalDateTime shiftStartTime = shiftSchedule.getStartTime();
        assertEquals("07:00 AM", shiftStartTime.format(timeFormatter));
    }

}
