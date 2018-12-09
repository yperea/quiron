package co.net.quiron.application.account;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Account manager test.
 */
class AccountManagerTest {

    AccountManager accountManager;
    IAppRepository<User> userRepository;
    IAppRepository<Person> personRepository;
    IAppRepository<Role> roleRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        accountManager = new AccountManager();
        userRepository = RepositoryFactory.getDBContext(User.class);
        personRepository = RepositoryFactory.getDBContext(Person.class);
        roleRepository = RepositoryFactory.getDBContext(Role.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test Signup.
     */
/*
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

        User user = userRepository.getListEquals("username", userName).get(0);
        Role role = roleRepository.get(roleId);
        Patient patient = (Patient) user.getPersons().stream().findFirst().get();
        Role userRole = user.getRoles().stream().findFirst().get();

        assertTrue(isSignedUp);
        assertEquals(userName, user.getUsername());
        assertEquals(firstName, patient.getFirstName());
        assertEquals(firstName, accountManager.getFirstName());
        assertEquals(true, accountManager.isSigned());
        assertEquals(role, userRole);
    }
*/

    /**
     * Test Signup.
     */
    @Test
    void testSignup() {

        String firstName = "Jane";
        String lastName = "Smith";
        String userName = "jnsmith";
        String email = "jnsmith@msn.com";
        String password = "1234";
        String confirmation = "1234";
        String birthDate = "06/15/1977";
        String gender = "M";
        String personType = null; //Patient
        int roleId = 2; //User

        boolean isSignedUp = accountManager.signup(personType, roleId, firstName, lastName,
                userName, email, birthDate, gender, password, confirmation);

        User user = userRepository.getListEquals("username", userName).get(0);
        Role role = roleRepository.get(roleId);
        Patient patient = (Patient) user.getPersons().stream().findFirst().get();
        Role userRole = user.getRoles().stream().findFirst().get();

        assertTrue(isSignedUp);
        assertEquals(userName, user.getUsername());
        assertEquals(firstName, patient.getFirstName());
        assertEquals(firstName, accountManager.getProfile().getFirstName());
        assertEquals(true, accountManager.isSigned());
        assertEquals(role, userRole);
    }

    /**
     * Test Load user account.
     */
    @Test
    void loadUserAccount() {
        String userName = "jsmith";
        String firstName = "John";
        String personType = "patient";

        accountManager = new AccountManager(userName, personType);
        accountManager.loadProfile();

        User user = userRepository.get(1);
        Person person = personRepository.get(1);

        assertEquals(userName, accountManager.getUsername());
        assertEquals(firstName, accountManager.getProfile().getFirstName());
        assertEquals(true, accountManager.isSigned());
    }
}