package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.Country;
import co.net.quiron.domain.admin.State;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test out The Countries manager.
 */
class CountryManagerTest {

    CountryManager countryManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        countryManager = new CountryManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting Country by its ID.
     */
    @Test
    void testGetCountryById() {
//        Country country = countryManager.getCountry(1);
        Country country = countryManager.get(1);
        assertEquals("United States", country.getName());
    }


    /**
     * Test the get all countries.
     */
    @Test
    void testGetAllCountries() {
        //List<Country> countryList = countryManager.getCountryList();
        List<Country> countryList = countryManager.getList();
        assertEquals(5, countryList.size());
    }


    /**
     * Test the country creation.
     */
    @Test
    void testCreateCountry() {

        String newCountryCode = "JP";
        String newCountryName = "Japan";
        Country newCountry = new Country(newCountryCode, newCountryName);
        Country insertedCountry = countryManager.create(newCountry);

        assertNotNull(insertedCountry);
        assertEquals("JP", insertedCountry.getCode());
        assertEquals("Japan", insertedCountry.getName());
        assertNotNull(insertedCountry.getCreatedDate());
    }

    /**
     * Test the country update.
     */
    @Test
    void testUpdateCountry() {

        String newCountryName = "Espana";
        int countryId = 4;
        Date currentModifiedDate = null;

        //Country countryToUpdate = countryManager.getCountry(countryId);
        Country countryToUpdate = countryManager.get(countryId);
        countryToUpdate.setName(newCountryName);
        currentModifiedDate = countryToUpdate.getModifiedDate();

        countryManager.update(countryToUpdate);
        //Country countryUpdated = countryManager.getCountry(countryId);
        Country countryUpdated = countryManager.get(countryId);

        assertEquals(countryToUpdate, countryUpdated);
        assertNotNull(countryUpdated.getModifiedDate());
        if (currentModifiedDate != null) {
            assertTrue(countryUpdated.getModifiedDate().compareTo(currentModifiedDate) > 0 );
        }
    }

    /**
     * Test the country deletion.
     */
    @Test
    void testDeleteCountry() {

        int countryIdToDelete = 3;
/*
        countryManager.delete(countryManager.getCountry(countryIdToDelete));
        assertNull(countryManager.getCountry(countryIdToDelete));
*/
        countryManager.delete(countryManager.get(countryIdToDelete));
        assertNull(countryManager.get(countryIdToDelete));

    }

    /**
     * Test create state.
     */
    @Test
    void testCreateCountryWithOneState() {
        Country newCountry = new Country("GB", "United Kingdom");
        State newState = new State("ENG", "England", newCountry);
        newCountry.addState(newState);

        Country createdCountry = countryManager.create(newCountry);

        assertNotNull(createdCountry);
        assertEquals(newCountry.getCode(), createdCountry.getCode());
        assertEquals(newCountry.getName(), createdCountry.getName());
        assertEquals(1, createdCountry.getStates().size());

    }

}