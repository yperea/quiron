package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.Country;
import co.net.quiron.domain.admin.State;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type States manager test.
 */
class StatesManagerTest {

    /**
     * The States manager.
     */
    StatesManager statesManager;
    CountriesManager countriesManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        statesManager = new StatesManager();
        countriesManager = new CountriesManager();

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get all states.
     */
    @Test
    void testGetAllStates() {
        List<State> stateList = statesManager.getStateList();
        assertEquals(10, stateList.size());
    }

    /**
     * Test get state by id.
     */
    @Test
    void testGetStateById() {
        State state = statesManager.getState(1);
        assertNotNull(state);
        assertEquals("Alaska", state.getName());
    }

    /**
     * Test create state.
     */
    @Test
    void testCreateState() {
        Country country = countriesManager.getCountry(1);
        State newState = new State("WI", "Wisconsin", country);
        country.addState(newState);
        State createdState = statesManager.create(newState);

        assertNotNull(createdState);
        assertEquals(newState.getCode(), createdState.getCode());
        assertEquals(newState.getName(), createdState.getName());

    }

    /**
     * Test update state.
     */
    @Test
    void testUpdateState() {
        State stateToUpdate = statesManager.getState(1);
        Country newCountry = countriesManager.getCountry(2);

        stateToUpdate.setCode("NS");
        stateToUpdate.setName("Nova Scotia");
        stateToUpdate.setCountry(newCountry);

        statesManager.update(stateToUpdate);
        State stateUpdated = statesManager.getState(1);

        assertEquals(stateToUpdate, stateUpdated);
    }

    /**
     * Test delete state.
     */
    @Test
    void testDeleteState() {
        statesManager.delete(statesManager.getState(1));
        assertNull(statesManager.getState(1));
    }
}