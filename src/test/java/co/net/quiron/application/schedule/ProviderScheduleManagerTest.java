package co.net.quiron.application.schedule;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.domain.schedule.*;
import co.net.quiron.test.util.DatabaseManager;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProviderScheduleManagerTest {
    EntityManager<ProviderSchedule> providerScheduleManager;
    EntityManager<ShiftSchedule> shiftScheduleManager;
    EntityManager<Provider> providerManager;
    EntityManager<Organization> organizationManager;
    EntityManager<Address> locationManager;
    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        providerScheduleManager = ManagerFactory.getManager(ProviderSchedule.class);
        shiftScheduleManager = ManagerFactory.getManager(ShiftSchedule.class);
        providerManager = ManagerFactory.getManager(Provider.class);
        organizationManager = ManagerFactory.getManager(Organization.class);
        locationManager = ManagerFactory.getManager(Address.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }


    /**
     * Test getting Shift by its ID.
     */
    @Test
    void testGetProviderScheduleById() {

        Map<String, Integer> shiftScheduleId = new TreeMap<>();
        shiftScheduleId.put("shift", 10);
        shiftScheduleId.put("weekDay", 5);

        ShiftSchedule shiftSchedule = shiftScheduleManager.get(shiftScheduleId);
        Provider provider = providerManager.get(3);
        Organization organization = organizationManager.get(1);
        Address location = locationManager.get(5);

        Map<String, Object> params = new TreeMap<>();
        params.put("shiftSchedule", shiftSchedule);
        params.put("provider", provider);
        params.put("organization", organization);
        params.put("address", location);

        List<ProviderSchedule> providerSchedule = providerScheduleManager.getListEquals(params);

        assertEquals("Weekday Morning Spring 2018", providerSchedule);
        //assertEquals("???", shift.getShiftSchedules().stream().findFirst().get());
    }

    /**
     * Test the get all shifts.
     */
    @Test
    void testGetAllProviderSchedules() {
        List<ProviderSchedule> shiftList = providerScheduleManager.getList();
        assertEquals(44, shiftList.size());
    }

    /**
     * Test the shift creation.
     */
    @Test
    void testCreateProviderSchedule() {

        Provider provider = (Provider) ManagerFactory.getManager(Provider.class).get(4);
        Shift shift = (Shift) ManagerFactory.getManager(Shift.class).get(11);
        WeekDay weekDay = (WeekDay) ManagerFactory.getManager(WeekDay.class).get(3);
        Organization organization = (Organization) ManagerFactory.getManager(Organization.class).get(2);


        String shiftName = "Weekday Morning Spring 2019";
        String shiftDescription = "Weekday Morning Spring 2019";
        LocalDate shiftStartDate = LocalDate.parse("2019-03-01");
        LocalDate shiftEndDate = LocalDate.parse("2019-05-31");
        String shiftStatus = "A";

        Shift newShift = new Shift(shiftName, shiftDescription, shiftStartDate, shiftEndDate, shiftStatus);
        //Shift insertedShift = shiftManager.create(newShift);

/*
        assertNotNull(insertedShift);
        assertEquals("Weekday Morning Spring 2019", insertedShift.getName());
        assertNotNull(insertedShift.getCreatedDate());
*/
    }

}
