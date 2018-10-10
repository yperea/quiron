package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.security.Role;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleManagerTest {

    EntityManager<Role> roleManager;

    @BeforeEach
    void setUp() {
        roleManager = new RoleManager();
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
     * Test create state.
     */
    @Test
    void testCreateRoleWithOneState() {
        //Role newRole = new Role("GB", "United Kingdom");
        //State newState = new State("ENG", "England", newRole);
        //newRole.addState(newState);

        //Role createdRole = roleManager.create(newRole);

        //assertNotNull(createdRole);
        //assertEquals(newRole.getCode(), createdRole.getCode());
        //assertEquals(newRole.getName(), createdRole.getName());
        //assertEquals(1, createdRole.getStates().size());

    }
}