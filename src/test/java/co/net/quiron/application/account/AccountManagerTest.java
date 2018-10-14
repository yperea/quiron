package co.net.quiron.application.account;

import co.net.quiron.application.admin.RoleManager;
import co.net.quiron.application.admin.UserManager;
import co.net.quiron.application.person.PersonManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.Person;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Account manager test.
 */
class AccountManagerTest {

    AccountManager accountManager;
    EntityManager<User> userManager;
    EntityManager<Person> personManager;
    EntityManager<Role> roleManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        accountManager = new AccountManager();
        userManager = new UserManager();
        personManager = new PersonManager();
        roleManager = new RoleManager();

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test Signup.
     */
    @Test
    void testSignup() {

        String firstName = "John";
        String lastName = "Smith";
        String userName = "jsmith";
        String email = "jsmith@msn.com";
        String password = "1324";
        String confirmation = "1324";
        int personTypeId = 3; //Patient
        int roleId = 2; //User

        boolean isSignedUp = accountManager.signup(personTypeId, roleId, firstName, lastName, userName, email, password, confirmation);

        User user = userManager.get(3);
        Role role = roleManager.get(roleId);

        Person person = user.getPersons().stream().findFirst().get();
        Role userRole = user.getRoles().stream().findFirst().get();



        assertTrue(isSignedUp);
        assertEquals(userName, user.getUsername());
        assertEquals(firstName, person.getFirstName());
        assertEquals(user, accountManager.getUser());
        assertEquals(person, accountManager.getPerson());
        assertEquals(role, userRole);

    }
}