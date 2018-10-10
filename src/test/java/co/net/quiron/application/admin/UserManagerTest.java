package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.security.User;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    EntityManager<User> userManager;
    
    @BeforeEach
    void setUp() {
        userManager = new UserManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test the get all users.
     */
    @Test
    void testGetUserById() {
        User user = userManager.get(1);
        assertEquals("yesper", user.getUsername());
    }


    /**
     * Test the get all users.
     */
    @Test
    void testGetAllUsers() {
        List<User> userList = userManager.getList();
        assertEquals(2, userList.size());
    }


    /**
     * Test the user creation.
     */
    @Test
    void testCreateUser() {

        String newUserName = "ypereamartinez";
        String newEmail = "ypereamartinez@madisoncollege.edu";
        String newPassword = "1234";
        User newUser = new User(newUserName, newEmail, newPassword);
        User insertedUser = userManager.create(newUser);

        assertNotNull(insertedUser);
        assertEquals("ypereamartinez", insertedUser.getUsername());
        assertEquals("ypereamartinez@madisoncollege.edu", insertedUser.getEmail());
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
     * Test create state.
     */
    @Test
    void testCreateUserWithOneState() {
        //User newUser = new User("GB", "United Kingdom");
        //State newState = new State("ENG", "England", newUser);
        //newUser.addState(newState);

        //User createdUser = userManager.create(newUser);

        //assertNotNull(createdUser);
        //assertEquals(newUser.getCode(), createdUser.getCode());
        //assertEquals(newUser.getName(), createdUser.getName());
        //assertEquals(1, createdUser.getStates().size());

    }

}