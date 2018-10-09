package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.Country;
import co.net.quiron.persistence.shared.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Holds the business logic to handle Countries operations.
 */
public class CountriesManager extends EntitiesManager<Country> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    //private IAppRepository<Country> countryRepository;

    /**
     * Instantiates a new Countries manager.
     */
    public CountriesManager() {
        super(Country.class);
        //countryRepository = new AppRepository<>(Country.class);
        logger.info("CountriesManager(): Instantiating CountriesManager class.");
    }

    /**
     * Gets the list of all of the Countries.
     *
     * @return the list of countries
     */
    /*public List<Country> getCountryList(){
        List<Country> countries = countryRepository.getAll();

        logger.debug("getCountryList(): Returning list of Countries.");
        logger.trace("getCountryList(): Returning " + countries);

        return countries;
    }*/

    /**
     * Gets a specific country by its id.
     *
     * @param id the id
     * @return the country
     */
    /*public Country getCountry(int id) {
        Country country = countryRepository.get(id);

        logger.debug("getCountry(): Returning Country.");
        logger.trace("getCountry(): Returning " + country);

        return country;
    }*/

    /**
     * Creates a new Country.
     *
     * @param country the country to be created
     * @return the country just created
     */
    /*public Country create (Country country) {

        logger.debug("create(Country): Creating Country.");
        logger.trace("create(Country): Creating " + country);

        return countryRepository.create(country);
    }
*/
    /**
     * Updates a specific Country.
     *
     * @param country the country to be updated
     */
  /*  public void update (Country country) {

        logger.debug("update(Country): Updating Country.");
        logger.trace("update(Country): Updating " + country);

        countryRepository.update(country);
    }
*/
    /**
     * Deletes a specific Country.
     *
     * @param country the country to be deleted
     */
  /*  public void delete (Country country) {

        logger.debug("delete(Country): Deleting Country.");
        logger.trace("delete(Country): Deleting " + country);

        countryRepository.delete(country);
    }*/
}
