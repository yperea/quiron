package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.security.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserManager extends EntityManager<User> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Countries manager.
     */
    public UserManager() {
        super(User.class);
        logger.info("UserManager(): Instantiating UserManager class.");
    }

}
