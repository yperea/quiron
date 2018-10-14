package co.net.quiron.application.admin;

import co.net.quiron.application.person.BusinessEntityManager;
import co.net.quiron.application.person.PersonManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.Person;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User manager test class.
 */
class UserManagerTest {

    EntityManager<User> userManager;
    EntityManager<Role> roleManager;
    EntityManager<BusinessEntity> entityManager;
    EntityManager<Person> personManager;


    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userManager = new UserManager();
        roleManager = new RoleManager();
        personManager = new PersonManager();
        entityManager = new BusinessEntityManager();

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get user by id.
     */
    @Test
    void testGetUserById() {
        User user = userManager.get(1);
        String username = user.getUsername();
        assertEquals("yperea", username );
    }

    /**
     * Test the get all users.
     */
    @Test
    void testGetAllUsers() {
        List<User> userList = userManager.getList();
        assertEquals(3, userList.size());
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
        User insertedUser = userManager.create(newUser);

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

        User userToUpdate = userManager.get(userId);
        userToUpdate.setUsername(newUserName);

        userManager.update(userToUpdate);
        User userUpdated = userManager.get(userId);

        assertEquals(userToUpdate, userUpdated);
        assertNotNull(userUpdated.getModifiedDate());
    }

    /**
     * Test the user deletion.
     */
    @Test
    void testDeleteUser() {

        int userIdToDelete = 2;

        userManager.delete(userManager.get(userIdToDelete));
        assertNull(userManager.get(userIdToDelete));
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

        User userToUpdate = userManager.get(userId);
        Role adminRole = roleManager.get(adminRoleId);
        Role userRole = roleManager.get(userRoleId);

        roles.add(adminRole);
        roles.add(userRole);

        userToUpdate.setRoles(roles);
        userManager.update(userToUpdate);

        User userUpdated = userManager.get(userId);

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

        User userToUpdate = userManager.get(userId);
        Role adminRole = roleManager.get(adminRoleId);

        userToUpdate.addRole(adminRole);
        userManager.update(userToUpdate);

        User userUpdated = userManager.get(userId);
        rolesAssigned = userUpdated.getRoles().size();
        Role newRole = userUpdated.getRoles().stream().findFirst().get();

        assertEquals(1,rolesAssigned);
        assertEquals(adminRole, newRole);
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

        Role userRole = roleManager.get(userRoleId);
        newUser.addRole(userRole);

        User insertedUser = userManager.create(newUser);

        rolesAssigned = insertedUser.getRoles().size();
        Role newRole = insertedUser.getRoles().stream().findFirst().get();

        assertNotNull(insertedUser);
        assertEquals("jsmith", insertedUser.getUsername());
        assertEquals("jsmith@aol.com", insertedUser.getEmail());
        assertEquals("1234", insertedUser.getPassword());
        assertNotNull(insertedUser.getCreatedDate());
        assertEquals(1,rolesAssigned);
        assertEquals(userRole, newRole);
    }

    /**
     * Test create user with a Person information.
     */
    @Test
    void testCreateUserWithAPerson() {
        String newUserName = "jsmith";
        String newEmail = "jsmith@aol.com";
        String newPassword = "1234";

        int userRoleId = 2; //User Role
        int rolesAssigned = 0;

        int personId = 1;
        int personAssigned = 0;

        User newUser = new User(newUserName, newEmail, newPassword);

        Role userRole = roleManager.get(userRoleId);
        newUser.addRole(userRole);

        User insertedUser = userManager.create(newUser);

        rolesAssigned = insertedUser.getRoles().size();
        Role newRole = insertedUser.getRoles().stream().findFirst().get();

        Person person = new Person(3, "John", "Smith");
        person.setEntity(entityManager.create(new BusinessEntity()));

        Person newPerson = personManager.create(person);

        newUser.addPerson(newPerson);
        userManager.update(newUser);
        personAssigned = userManager.get(newPerson.getId()).getPersons().size();

        assertNotNull(insertedUser);
        assertEquals("jsmith", insertedUser.getUsername());
        assertEquals("jsmith@aol.com", insertedUser.getEmail());
        assertEquals("1234", insertedUser.getPassword());
        assertNotNull(insertedUser.getCreatedDate());
        assertEquals(1,rolesAssigned);
        assertEquals(1,personAssigned);
        assertEquals(userRole, newRole);
        assertNotNull(newPerson);
        assertEquals(3, newPerson.getId());
        assertEquals("John", newPerson.getFirstName());
        assertEquals("Smith", newPerson.getLastName());

    }


}