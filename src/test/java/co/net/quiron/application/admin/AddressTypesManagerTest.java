package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.AddressType;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test out The Address Types manager.
 */
class AddressTypesManagerTest {

    /**
     * Creates an AddressTypes manager object.
     */
    AddressTypesManager addressTypesManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        addressTypesManager = new AddressTypesManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test the get all address types.
     */
    @Test
    void testGetAllAddressTypes() {
        List<AddressType> addressTypeList = addressTypesManager.getAll();
        assertEquals(5, addressTypeList.size());
    }

    /**
     * Test the address type creation.
     */
    @Test
    void testInsertAddressType() {

        String newAddressTypeName = "Headquarter";
        AddressType addressType = new AddressType(newAddressTypeName);
        int id = addressTypesManager.create(addressType);

        AddressType insertedAddressType = addressTypesManager.get(id);

        assertNotEquals(0, id);
        assertEquals("Headquarter", insertedAddressType.getName());
        assertNotNull(insertedAddressType.getCreatedDate());
    }

    /**
     * Test the address type update.
     */
    @Test
    void testUpdateAddressType() {

        String newAddressTypeName = "Main Office";
        int addressTypeId = 3;
        Date currentModifiedDate = null;

        AddressType addressTypeToUpdate = addressTypesManager.get(addressTypeId);
        addressTypeToUpdate.setName(newAddressTypeName);
        currentModifiedDate = addressTypeToUpdate.getModifiedDate();

        addressTypesManager.update(addressTypeToUpdate);
        AddressType addressTypeUpdated = addressTypesManager.get(addressTypeId);

        assertEquals(newAddressTypeName, addressTypeUpdated.getName());
        assertNotNull(addressTypeUpdated.getModifiedDate());
        if (currentModifiedDate != null) {
            assertTrue(addressTypeUpdated.getModifiedDate().compareTo(currentModifiedDate) > 0 );
        }
    }

    /**
     * Test the address type deletion.
     */
    @Test
    void testDeleteAddressType() {

        int addressTypeIdToDelete = 3;
        addressTypesManager.delete(addressTypesManager.get(addressTypeIdToDelete));
        assertNull(addressTypesManager.get(addressTypeIdToDelete));
    }
}