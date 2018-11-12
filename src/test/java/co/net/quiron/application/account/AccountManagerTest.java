package co.net.quiron.application.account;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.Patient;
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
        userManager = ManagerFactory.getManager(User.class);
        personManager = ManagerFactory.getManager(Person.class);
        roleManager = ManagerFactory.getManager(Role.class);

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
        String birthDate = "06/15/1977";
        String gender = "M";
        int personTypeId = 3; //Patient
        int roleId = 2; //User

        boolean isSignedUp = accountManager.signup(personTypeId, roleId, firstName, lastName,
                                                    userName, email, birthDate, gender, password, confirmation);

        User user = userManager.getListEquals("username", userName).get(0);
        Role role = roleManager.get(roleId);
        Patient patient = (Patient) user.getPersons().stream().findFirst().get();
        Role userRole = user.getRoles().stream().findFirst().get();

        assertTrue(isSignedUp);
        assertEquals(userName, user.getUsername());
        assertEquals(firstName, patient.getFirstName());
        assertEquals(firstName, accountManager.getFirstName());
        assertEquals(true, accountManager.isSigned());
        assertEquals(role, userRole);
    }

    /**
     * Test Load user account.
     */
    @Test
    void loadUserAccount() {
        String userName = "yesper";
        String firstName = "Yesid";
        String personType = "patient";

        accountManager.loadUserAccount(userName, personType);

        User user = userManager.get(1);
        Person person = personManager.get(1);

        assertEquals(userName, accountManager.getUsername());
        assertEquals(firstName, accountManager.getFirstName());
        assertEquals(true, accountManager.isSigned());
    }
}