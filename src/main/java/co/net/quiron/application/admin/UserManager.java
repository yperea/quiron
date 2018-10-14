package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Holds the business logic to handle the Users.
 */
public class UserManager extends EntityManager<User> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new User Manager.
     */
    public UserManager() {
        super(User.class);
        logger.info("UserManager(): Instantiating UserManager class.");
    }

}
