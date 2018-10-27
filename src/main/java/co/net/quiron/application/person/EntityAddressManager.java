package co.net.quiron.application.person;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.EntityAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles Entity addresses operations.
 */
public class EntityAddressManager extends EntityManager<EntityAddress> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Entity address manager.
     */
    public EntityAddressManager() {
        super(EntityAddress.class);
        logger.info("EntityAddressManager(): Instantiating EntityAddressManager class.");
    }
}
