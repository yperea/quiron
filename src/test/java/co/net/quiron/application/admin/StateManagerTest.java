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
class StateManagerTest {

    /**
     * The States manager.
     */
    StateManager stateManager;
    CountryManager countryManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        stateManager = new StateManager();
        countryManager = new CountryManager();

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get all states.
     */
    @Test
    void testGetAllStates() {
        List<State> stateList = stateManager.getList();
        assertEquals(10, stateList.size());
    }

    /**
     * Test get state by id.
     */
    @Test
    void testGetById() {
        State state = stateManager.get(1);
        assertNotNull(state);
        assertEquals("Alaska", state.getName());
    }

    /**
     * Test create state.
     */
    @Test
    void testCreateState() {
        //Country country = countryManager.getCountry(1);
        Country country = countryManager.get(1);
        State newState = new State("WI", "Wisconsin", country);
        country.addState(newState);
        State createdState = stateManager.create(newState);

        assertNotNull(createdState);
        assertEquals(newState.getCode(), createdState.getCode());
        assertEquals(newState.getName(), createdState.getName());

    }

    /**
     * Test update state.
     */
    @Test
    void testUpdateState() {
        State stateToUpdate = stateManager.get(1);
        //Country newCountry = countryManager.getCountry(2);
        Country newCountry = countryManager.get(2);

        stateToUpdate.setCode("NS");
        stateToUpdate.setName("Nova Scotia");
        stateToUpdate.setCountry(newCountry);

        stateManager.update(stateToUpdate);
        State stateUpdated = stateManager.get(1);

        assertEquals(stateToUpdate, stateUpdated);
    }

    /**
     * Test delete state.
     */
    @Test
    void testDeleteState() {
        stateManager.delete(stateManager.get(1));
        assertNull(stateManager.get(1));
    }
}