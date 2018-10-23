package co.net.quiron.application.account;

import co.net.quiron.application.account.RoleManager;
import co.net.quiron.application.account.UserManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Role manager test.
 */
class RoleManagerTest {

    /**
     * The Role manager.
     */
    EntityManager<Role> roleManager;
    /**
     * The User manager.
     */
    EntityManager<User> userManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        roleManager = new RoleManager();
        userManager = new UserManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test the get all roles.
     */
    @Test
    void testGetRoleById() {
        Role role = roleManager.get(1);
        assertEquals("Administrator", role.getName());
    }


    /**
     * Test the get all roles.
     */
    @Test
    void testGetAllRoles() {
        List<Role> roleList = roleManager.getList();
        assertEquals(2, roleList.size());
    }


    /**
     * Test the role creation.
     */
    @Test
    void testCreateRole() {

        String newRoleName = "Patient";
        String newDescription = "Patient role";
        Role newRole = new Role(newRoleName, newDescription);
        Role insertedRole = roleManager.create(newRole);

        assertNotNull(insertedRole);
        assertEquals("Patient", insertedRole.getName());
        assertEquals("Patient role", insertedRole.getDescription());
        assertNotNull(insertedRole.getCreatedDate());
    }

    /**
     * Test the role update.
     */
    @Test
    void testUpdateRole() {

        int roleId = 1;
        String newRoleName = "Admin";

        Role roleToUpdate = roleManager.get(roleId);
        roleToUpdate.setName(newRoleName);

        roleManager.update(roleToUpdate);
        Role roleUpdated = roleManager.get(roleId);

        assertEquals(roleToUpdate, roleUpdated);
        assertNotNull(roleUpdated.getModifiedDate());
    }

    //TODO: Create Test Case for Roles with children
    /**
     * Test the role deletion.
     */
    @Test
    void testDeleteRole() {

        int roleIdToDelete = 2;

        roleManager.delete(roleManager.get(roleIdToDelete));
        assertNull(roleManager.get(roleIdToDelete));
    }

    /**
     * Test adding set of users to existent role.
     */
    /*
    @Test
    void testAddingSetOfUsersToExistentRole() {
        int firstUserId = 1;
        int secondUserId = 2;

        int roleId = 2;
        int usersAssigned = 0;

        Set<User> users = new HashSet<>();

        Role roleToUpdate = roleManager.get(roleId);
        User firstUser = userManager.get(firstUserId);
        User secondUser = userManager.get(secondUserId);

        users.add(firstUser);
        users.add(secondUser);

        roleToUpdate.setUsers(users);
        roleManager.update(roleToUpdate);

        Role roleUpdated = roleManager.get(roleId);

        usersAssigned = roleUpdated.getUsers().size();

        assertEquals(2,usersAssigned);
    }
    */
}