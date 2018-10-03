package co.net.quiron.application.admin;

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

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        statesManager = new StatesManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get all states.
     */
    @Test
    void testGetAllStates() {
        List<State> stateList = statesManager.getAll();
        assertEquals(10, stateList.size());
    }

    /**
     * Test get state by id.
     */
    @Test
    void testGetStateById() {
        State state = statesManager.get(1);
        assertNotNull(state);
        assertEquals("Alazka", state.getName());
    }

    /**
     * Test create state.
     */
    @Test
    void testCreateState() {
    }

    /**
     * Test update state.
     */
    @Test
    void testUpdateState() {
    }

    /**
     * Test delete state.
     */
    @Test
    void testDeleteState() {

    }
}