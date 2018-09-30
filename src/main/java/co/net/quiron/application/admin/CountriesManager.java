package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.Country;
import co.net.quiron.persistence.shared.*;
import java.util.List;

/**
 * Holds the business logic to handle Countries operations.
 */
public class CountriesManager {

    private IAppRepository<Country> countryRepository;

    /**
     * Instantiates a new Countries manager.
     */
    public CountriesManager() {
        countryRepository = new AppRepository<>(Country.class);
    }

    /**
     * Gets the list of all of the Countries.
     *
     * @return the list of countries
     */
    public List<Country> getAll(){
        return countryRepository.getAll();
    }

    /**
     * Gets a specific country by its id.
     *
     * @param id the id
     * @return the country
     */
    public Country get(int id) {
        return countryRepository.get(id);
    }

    /**
     * Creates a new Country.
     *
     * @param country the country to be created
     * @return the int
     */
    public int create (Country country) {
        int i = countryRepository.create(country);
        return i;
    }

    /**
     * Updates a specific Country.
     *
     * @param country the country to be updated
     */
    public void update (Country country) {
        countryRepository.update(country);
    }

    /**
     * Deletes a specific Country.
     *
     * @param country the country to be deleted
     */
    public void delete (Country country) {
        countryRepository.delete(country);
    }
}
