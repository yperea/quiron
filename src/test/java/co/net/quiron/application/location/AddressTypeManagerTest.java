package co.net.quiron.application.location;

import co.net.quiron.application.admin.AddressTypeManager;
import co.net.quiron.domain.location.AddressType;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressTypeManagerTest {

    AddressTypeManager addressTypeManager;

    @BeforeEach
    void setUp() {
        addressTypeManager = new AddressTypeManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting AddressType by its ID.
     */
    @Test
    void testGetAddressTypeById() {
        AddressType addressType = addressTypeManager.get(1);
        assertEquals("Home", addressType.getName());
    }

    /**
     * Test the get all addressTypes.
     */
    @Test
    void testGetAllAddressTypes() {
        List<AddressType> addressTypeList = addressTypeManager.getList();
        assertEquals(4, addressTypeList.size());
    }


    /**
     * Test the addressType creation.
     */
    @Test
    void testCreateAddressType() {

        String newAddressTypeName = "Main";
        AddressType newAddressType = new AddressType(newAddressTypeName);
        AddressType insertedAddressType = addressTypeManager.create(newAddressType);

        assertNotNull(insertedAddressType);
        assertEquals("Main", insertedAddressType.getName());
        assertNotNull(insertedAddressType.getCreatedDate());
    }

    /**
     * Test the addressType update.
     */
    @Test
    void testUpdateAddressType() {

        String newAddressTypeName = "Main";
        int addressTypeId = 3;

        AddressType addressTypeToUpdate = addressTypeManager.get(addressTypeId);
        addressTypeToUpdate.setName(newAddressTypeName);

        addressTypeManager.update(addressTypeToUpdate);
        AddressType addressTypeUpdated = addressTypeManager.get(addressTypeId);

        assertEquals(addressTypeToUpdate, addressTypeUpdated);
        assertNotNull(addressTypeUpdated.getModifiedDate());
    }

    /*TODO: Implement a Test Case for deleting an Addresstype with linked addresses */
    /**
     * Test the addressType deletion.
     */
    @Test
    void testDeleteAddressType() {

        int addressTypeIdToDelete = 5;
        String newAddressTypeName = "Main";
        AddressType newAddressType = new AddressType(newAddressTypeName);
        AddressType insertedAddressType = addressTypeManager.create(newAddressType);
        addressTypeManager.delete(insertedAddressType);
        assertNull(addressTypeManager.get(addressTypeIdToDelete));
    }
}