package co.net.quiron.persistence.location;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.location.Country;
import co.net.quiron.domain.location.State;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test out The Countries repository.
 */
class CountryRepositoryTest {

    IAppRepository<Country> countryRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        countryRepository = RepositoryFactory.getDBContext(Country.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting Country by its ID.
     */
    @Test
    void testGetCountryById() {
//        Country country = countryRepository.getCountry(1);
        Country country = countryRepository.get(1);
        assertEquals("United States", country.getName());
    }


    /**
     * Test the get all countries.
     */
    @Test
    void testGetAllCountries() {
        //List<Country> countryList = countryRepository.getCountryList();
        List<Country> countryList = countryRepository.getList();
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
        Country insertedCountry = countryRepository.create(newCountry);

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

        Country countryToUpdate = countryRepository.get(countryId);
        countryToUpdate.setName(newCountryName);
        currentModifiedDate = countryToUpdate.getModifiedDate();

        countryRepository.update(countryToUpdate);
        Country countryUpdated = countryRepository.get(countryId);

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

        String newCountryCode = "JP";
        String newCountryName = "Japan";
        Country newCountry = new Country(newCountryCode, newCountryName);
        Country insertedCountry = countryRepository.create(newCountry);

        int countryIdToDelete = insertedCountry.getId();
        countryRepository.delete(countryRepository.get(countryIdToDelete));
        assertNull(countryRepository.get(countryIdToDelete));
    }

    /**
     * Test create state.
     */
    @Test
    void testCreateCountryWithOneState() {
        Country newCountry = new Country("GB", "United Kingdom");
        State newState = new State("ENG", "England", newCountry);
        newCountry.addState(newState);

        Country createdCountry = countryRepository.create(newCountry);

        assertNotNull(createdCountry);
        assertEquals(newCountry.getCode(), createdCountry.getCode());
        assertEquals(newCountry.getName(), createdCountry.getName());
        assertEquals(1, createdCountry.getStates().size());
    }
}