package co.net.quiron.persistence.location;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.location.Country;
import co.net.quiron.domain.location.State;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type States repository test.
 */
class StateRepositoryTest {

    /**
     * The States repository.
     */
    IAppRepository<State> stateRepository;
    IAppRepository<Country> countryRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        stateRepository = RepositoryFactory.getDBContext(State.class);
        countryRepository = RepositoryFactory.getDBContext(Country.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get all states.
     */
    @Test
    void testGetAllStates() {
        List<State> stateList = stateRepository.getList();
        assertEquals(66, stateList.size());
    }

    /**
     * Test get state by id.
     */
    @Test
    void testGetById() {
        State state = stateRepository.get(1);
        assertNotNull(state);
        assertEquals("Alberta", state.getName());
    }

    /**
     * Test create state.
     */
    @Test
    void testCreateState() {
        //Country country = countryRepository.getCountry(1);
        Country country = countryRepository.get(1);
        State newState = new State("WI", "Wisconsin", country);
        country.addState(newState);
        State createdState = stateRepository.create(newState);

        assertNotNull(createdState);
        assertEquals(newState.getCode(), createdState.getCode());
        assertEquals(newState.getName(), createdState.getName());

    }

    /**
     * Test update state.
     */
    @Test
    void testUpdateState() {
        State stateToUpdate = stateRepository.get(1);
        //Country newCountry = countryRepository.getCountry(2);
        Country newCountry = countryRepository.get(2);

        stateToUpdate.setCode("NS");
        stateToUpdate.setName("Nova Scotia");
        stateToUpdate.setCountry(newCountry);

        stateRepository.update(stateToUpdate);
        State stateUpdated = stateRepository.get(1);

        assertEquals("Nova Scotia", stateUpdated.getName());
    }

    /**
     * Test delete state.
     */
    @Test
    void testDeleteState() {
        stateRepository.delete(stateRepository.get(1));
        assertNull(stateRepository.get(1));
    }
}