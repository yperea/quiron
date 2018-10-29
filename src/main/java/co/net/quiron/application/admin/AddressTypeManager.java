package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.location.AddressType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles Address Type operations.
 */
public class AddressTypeManager extends EntityManager<AddressType> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Address type manager.
     */
    public AddressTypeManager() {
        super(AddressType.class);
        logger.info("AddressTypeManager(): Instantiating AddressTypeManager class.");
    }

}
