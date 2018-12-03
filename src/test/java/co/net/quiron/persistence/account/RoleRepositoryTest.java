package co.net.quiron.persistence.account;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Role manager test.
 */
class RoleRepositoryTest {

    IAppRepository<Role> roleRepository;
    IAppRepository<User> userRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        roleRepository = RepositoryFactory.getDBContext(Role.class);
        userRepository = RepositoryFactory.getDBContext(User.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test the get all roles.
     */
    @Test
    void testGetRoleById() {
        Role role = roleRepository.get(1);
        assertEquals("Administrator", role.getName());
    }


    /**
     * Test the get all roles.
     */
    @Test
    void testGetAllRoles() {
        List<Role> roleList = roleRepository.getList();
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
        Role insertedRole = roleRepository.create(newRole);

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

        Role roleToUpdate = roleRepository.get(roleId);
        roleToUpdate.setName(newRoleName);

        roleRepository.update(roleToUpdate);
        Role roleUpdated = roleRepository.get(roleId);

        assertEquals(newRoleName, roleUpdated.getName());
        assertNotNull(roleUpdated.getModifiedDate());
    }

    //TODO: Create Test Case for Roles with children
    /**
     * Test the role deletion.
     */
    @Test
    void testDeleteRole() {

        int roleIdToDelete = 2;

        roleRepository.delete(roleRepository.get(roleIdToDelete));
        assertNull(roleRepository.get(roleIdToDelete));
    }
}