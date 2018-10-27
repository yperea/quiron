package co.net.quiron.application.person;

import co.net.quiron.application.admin.StateManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.admin.State;
import co.net.quiron.domain.person.Address;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Address manager tester.
 */
class AddressManagerTest {

    EntityManager<Address> addressManager;
    EntityManager<State> stateManager;

    /**
     * Initializes managers and data for the test.
     */
    @BeforeEach
    void setUp() {
        addressManager = new AddressManager();
        stateManager = new StateManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get address by id.
     */
    @Test
    void testGetAddressByID() {
        Address address = addressManager.get(1);

        assertNotNull(address);
        assertEquals("1701 Wright Street", address.getAddressLine1());
    }

    /**
     * Test get all addresses.
     */
    @Test
    void testGetAllAddresses() {
        List<Address> addressList = addressManager.getList();
        assertEquals(2,addressList.size());
    }

    /**
     * Test create address.
     */
    @Test
    void testCreateAddress() {

        State state = stateManager.get(6);
        String address1 = "211 North Carroll Street";
        String city = "Madison";
        String postalCode = "53703";

        Address newAddress = new Address(address1, city, state, postalCode);
        Address createAddress = addressManager.create(newAddress);

        assertNotNull(createAddress);
        assertEquals(newAddress.getAddressLine1(), createAddress.getAddressLine1());
    }

    /**
     * Test update address.
     */
    @Test
    void testUpdateAddress() {

        Address address = addressManager.get(1);
        State state = stateManager.get(5);
        String address1 = "2125 Commercial Avenue";
        address.setAddressLine1(address1);
        address.setState(state);
        addressManager.update(address);
        Address updatedAddress = addressManager.get(1);

        assertEquals(address1, updatedAddress.getAddressLine1());
        assertEquals(address, updatedAddress);
    }

    /**
     * Test delete address.
     */
    @Test
    void testDeleteAddress() {

        addressManager.delete(addressManager.get(1));
        assertNull(addressManager.get(1));
    }

}