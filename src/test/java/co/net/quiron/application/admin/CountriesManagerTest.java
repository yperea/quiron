package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.Country;
import co.net.quiron.domain.admin.State;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test out The Countries manager.
 */
class CountriesManagerTest {

    /**
     * Creates an Countries manager object.
     */
    CountriesManager countriesManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        countriesManager = new CountriesManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test the get all countries.
     */
    @Test
    void testGetCountryById() {
//        Country country = countriesManager.getCountry(1);
        Country country = countriesManager.get(1);
        assertEquals("United States", country.getName());
    }


    /**
     * Test the get all countries.
     */
    @Test
    void testGetAllCountries() {
        //List<Country> countryList = countriesManager.getCountryList();
        List<Country> countryList = countriesManager.getList();
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
        Country insertedCountry = countriesManager.create(newCountry);

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

        //Country countryToUpdate = countriesManager.getCountry(countryId);
        Country countryToUpdate = countriesManager.get(countryId);
        countryToUpdate.setName(newCountryName);
        currentModifiedDate = countryToUpdate.getModifiedDate();

        countriesManager.update(countryToUpdate);
        //Country countryUpdated = countriesManager.getCountry(countryId);
        Country countryUpdated = countriesManager.get(countryId);

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
        countriesManager.delete(countriesManager.getCountry(countryIdToDelete));
        assertNull(countriesManager.getCountry(countryIdToDelete));
*/
        countriesManager.delete(countriesManager.get(countryIdToDelete));
        assertNull(countriesManager.get(countryIdToDelete));

    }

    /**
     * Test create state.
     */
    @Test
    void testCreateCountryWithOneState() {
        Country newCountry = new Country("GB", "United Kingdom");
        State newState = new State("ENG", "England", newCountry);
        newCountry.addState(newState);

        Country createdCountry = countriesManager.create(newCountry);

        assertNotNull(createdCountry);
        assertEquals(newCountry.getCode(), createdCountry.getCode());
        assertEquals(newCountry.getName(), createdCountry.getName());
        assertEquals(1, createdCountry.getStates().size());

    }

}