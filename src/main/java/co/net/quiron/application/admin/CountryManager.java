package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.location.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handle Countries operations.
 */
public class CountryManager extends EntityManager<Country> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Countries manager.
     */
    public CountryManager() {
        super(Country.class);
        logger.info("CountryManager(): Instantiating CountryManager class.");
    }

}
