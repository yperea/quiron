package co.net.quiron.application.account;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Holds the business logic to handle the Roles.
 */
public class RoleManager extends EntityManager<Role> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Role manager.
     */
    public RoleManager() {
        super(Role.class);
        logger.info("RoleManager(): Instantiating RoleManager class.");
    }
}