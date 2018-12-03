package co.net.quiron.persistence.account;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.Person;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User Repository test class.
 */
class UserRepositoryTest {

    IAppRepository<User> userRepository;
    IAppRepository<Role> roleRepository;
    IAppRepository<Person> personRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userRepository = RepositoryFactory.getDBContext(User.class);
        roleRepository = RepositoryFactory.getDBContext(Role.class);
        personRepository = RepositoryFactory.getDBContext(Person.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get user by id.
     */
    @Test
    void testGetUserById() {
        /*User user = userRepository.get(1);*/
        User user = userRepository.get(1);
        String username = user.getUsername();
        assertEquals("admin", username );
    }

    /**
     * Test the get all users.
     */
    @Test
    void testGetAllUsers() {
        List<User> userList = userRepository.getList();
        assertEquals(4, userList.size());
    }

    /**
     * Test the get all users by property.
     */
    @Test
    void testGetUsersEquals() {

        String username = "admin";
        List<User> userList = userRepository.getListEquals("username", username);
        assertEquals(1, userList.size());
    }

    /**
     * Test the get all users by Username.
     */
    @Test
    void testGetUsersLike() {

        String username = "r";
        List<User> userList = userRepository.getListContains("username", username);
        assertEquals(2, userList.size());
    }


    /**
     * Test the user creation.
     */
    @Test
    void testCreateUser() {

        String newUserName = "jsmith";
        String newEmail = "jsmith@aol.com";
        String newPassword = "1234";
        User newUser = new User(newUserName, newEmail, newPassword);
        User insertedUser = userRepository.create(newUser);

        assertNotNull(insertedUser);
        assertEquals("jsmith", insertedUser.getUsername());
        assertEquals("jsmith@aol.com", insertedUser.getEmail());
        assertEquals("1234", insertedUser.getPassword());
        assertNotNull(insertedUser.getCreatedDate());
    }

    /**
     * Test the user update.
     */
    @Test
    void testUpdateUser() {

        String newUserName = "yperea";
        int userId = 1;

        User userToUpdate = userRepository.get(userId);
        userToUpdate.setUsername(newUserName);

        userRepository.update(userToUpdate);
        User userUpdated = userRepository.get(userId);

        assertEquals(newUserName, userUpdated.getUsername());
        assertNotNull(userUpdated.getModifiedDate());
    }

    /**
     * Test the user deletion.
     */
    @Test
    void testDeleteUser() {

        int userIdToDelete = 1;

        /*TODO: Check DB Constraints scenario when a user is deleted, the person related to it is deleted too. */

        userRepository.delete(userRepository.get(userIdToDelete));
        assertNull(userRepository.get(userIdToDelete));
    }

    /**
     * Test adding set of roles to existent user.
     */
    @Test
    void testAddingSetOfRolesToExistentUser() {
        int userId = 1;
        int adminRoleId = 1;
        int userRoleId = 2;
        int rolesAssigned = 0;

        Set<Role> roles = new HashSet<>();

        User userToUpdate = userRepository.get(userId);
        Role adminRole = roleRepository.get(adminRoleId);
        Role userRole = roleRepository.get(userRoleId);

        roles.add(adminRole);
        roles.add(userRole);

        userToUpdate.setRoles(roles);
        userRepository.update(userToUpdate);

        User userUpdated = userRepository.get(userId);

        rolesAssigned = userUpdated.getRoles().size();

        assertEquals(2,rolesAssigned);
    }

    /**
     * Test adding role to existent user.
     */
    @Test
    void testAddingRoleToExistentUser() {
        int userId = 1;
        int adminRoleId = 1;
        int rolesAssigned = 0;

        User userToUpdate = userRepository.get(userId);
        Role adminRole = roleRepository.get(adminRoleId);

        userToUpdate.addRole(adminRole);
        userRepository.update(userToUpdate);

        User userUpdated = userRepository.get(userId);
        rolesAssigned = userUpdated.getRoles().size();
        Role newRole = userUpdated.getRoles().stream().findFirst().get();

        assertEquals(1,rolesAssigned);
        assertEquals(adminRole.getName(), newRole.getName());
    }

    /**
     * Test create user with one role.
     */
    @Test
    void testCreateUserWithOneRole() {
        String newUserName = "jsmith";
        String newEmail = "jsmith@aol.com";
        String newPassword = "1234";

        int userRoleId = 2; //User Role
        int rolesAssigned = 0;

        User newUser = new User(newUserName, newEmail, newPassword);

        Role userRole = roleRepository.get(userRoleId);
        newUser.addRole(userRole);

        User insertedUser = userRepository.create(newUser);

        rolesAssigned = insertedUser.getRoles().size();
        Role newRole = insertedUser.getRoles().stream().findFirst().get();

        assertNotNull(insertedUser);
        assertEquals("jsmith", insertedUser.getUsername());
        assertEquals("jsmith@aol.com", insertedUser.getEmail());
        assertEquals("1234", insertedUser.getPassword());
        assertNotNull(insertedUser.getCreatedDate());
        assertEquals(1,rolesAssigned);
        assertEquals(userRole.getName(), newRole.getName());
    }
}