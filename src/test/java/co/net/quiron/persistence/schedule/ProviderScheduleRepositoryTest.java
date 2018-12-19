package co.net.quiron.persistence.schedule;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.domain.schedule.*;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProviderScheduleRepositoryTest {
    IAppRepository<ProviderSchedule> providerScheduleRepository;
    IAppRepository<ShiftSchedule> shiftScheduleRepository;
    IAppRepository<Provider> providerRepository;
    IAppRepository<Organization> organizationRepository;
    IAppRepository<Address> locationRepository;
    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        providerScheduleRepository = RepositoryFactory.getDBContext(ProviderSchedule.class);
        shiftScheduleRepository = RepositoryFactory.getDBContext(ShiftSchedule.class);
        providerRepository = RepositoryFactory.getDBContext(Provider.class);
        organizationRepository = RepositoryFactory.getDBContext(Organization.class);
        locationRepository = RepositoryFactory.getDBContext(Address.class);

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

        ShiftSchedule shiftSchedule = shiftScheduleRepository.get(shiftScheduleId);
        Provider provider = providerRepository.get(3);
        Organization organization = organizationRepository.get(1);
        Address location = locationRepository.get(5);

        Map<String, Object> params = new TreeMap<>();
        params.put("shiftSchedule", shiftSchedule);
        params.put("provider", provider);
        params.put("organization", organization);
        params.put("address", location);

        List<ProviderSchedule> providerSchedule = providerScheduleRepository.getListEquals(params);

        assertEquals("Weekday Morning Winter 2018", providerSchedule.stream().findFirst().get().getShiftSchedule().getShift().getName());
        //assertEquals("???", shift.getShiftSchedules().stream().findFirst().get());
    }

    /**
     * Test the get all shifts.
     */
    @Test
    void testGetAllProviderSchedules() {
        List<ProviderSchedule> shiftList = providerScheduleRepository.getList();
        assertEquals(44, shiftList.size());
    }

    /**
     * Test the shift creation.
     */
    @Test
    void testCreateProviderSchedule() {

        Map<String, Integer> shiftScheduleId = new TreeMap<>();
        shiftScheduleId.put("shift", 12);
        shiftScheduleId.put("weekDay", 6);

        ShiftSchedule shiftSchedule = shiftScheduleRepository.get(shiftScheduleId);
        Provider provider = providerRepository.get(3);
        Organization organization = organizationRepository.get(1);
        Address location = locationRepository.get(5);

        ProviderSchedule providerSchedule = new ProviderSchedule(provider, shiftSchedule, location, organization);

        providerScheduleRepository.update(providerSchedule);

        Map<String, Object> params = new TreeMap<>();
        params.put("shiftSchedule", shiftSchedule);
        params.put("provider", provider);
        params.put("organization", organization);
        params.put("address", location);

        ProviderSchedule insertedProviderSchedule =  providerScheduleRepository.getListEquals(params).get(0);

        assertNotNull(insertedProviderSchedule);

    }

}
