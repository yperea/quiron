package co.net.quiron.application.person;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.location.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles Address entity operations.
 */
public class AddressManager extends EntityManager<Address> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Entities manager.
     */
    public AddressManager() {
        super(Address.class);
        logger.info("AddressManager(): Instantiating AddressManager class.");
    }
}
