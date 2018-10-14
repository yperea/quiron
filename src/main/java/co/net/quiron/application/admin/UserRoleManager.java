package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type User role manager.
 */
public class UserRoleManager extends EntityManager<UserRole> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * Instantiates a new User role manager.
     */
    public UserRoleManager() {
        super(UserRole.class);
    }
}
