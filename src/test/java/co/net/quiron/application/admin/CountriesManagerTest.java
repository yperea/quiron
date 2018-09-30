package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.Country;
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
    void testGetAllCountries() {
        List<Country> countryList = countriesManager.getAll();
        assertEquals(5, countryList.size());
    }

    /**
     * Test the country creation.
     */
    @Test
    void testInsertCountry() {

        String newCountryCode = "JP";
        String newCountryName = "Japan";
        Country country = new Country(newCountryCode, newCountryName);
        int id = countriesManager.create(country);

        Country insertedCountry = countriesManager.get(id);

        assertNotEquals(0, id);
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

        Country countryToUpdate = countriesManager.get(countryId);
        countryToUpdate.setName(newCountryName);
        currentModifiedDate = countryToUpdate.getModifiedDate();

        countriesManager.update(countryToUpdate);
        Country countryUpdated = countriesManager.get(countryId);

        assertEquals(newCountryName, countryUpdated.getName());
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
        countriesManager.delete(countriesManager.get(countryIdToDelete));
        assertNull(countriesManager.get(countryIdToDelete));
    }
}