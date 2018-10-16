package co.net.quiron.application.account;

import co.net.quiron.application.account.RoleManager;
import co.net.quiron.application.account.UserManager;
import co.net.quiron.application.account.UserRoleManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.account.UserRole;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;

class UserRoleManagerTest {

    EntityManager<User> userManager;
    EntityManager<Role> roleManager;
    EntityManager<UserRole> userRoleManager;

    @BeforeEach
    void setUp() {
        userManager = new UserManager();
        roleManager = new RoleManager();
        userRoleManager = new UserRoleManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");

    }

    /*
    @Test
    void testAddingRoleToExistentUser() {

        int userId = 1;
        int adminRoleId = 1;
        int rolesAssigned = 0;

        User user = userManager.get(userId);
        Role role = roleManager.get(adminRoleId);

        UserRole userRole = new UserRole(user, role);

        userRoleManager.create(userRole);

        assertEquals(1,rolesAssigned);

    }
    */

}