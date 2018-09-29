package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.AddressType;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressTypesManagerTest {

    AddressTypesManager addressTypesManager;

    @BeforeEach
    void setUp() {
        addressTypesManager = new AddressTypesManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    @Test
    void testGetAllAddressTypes() {
        List<AddressType> addressTypeList = addressTypesManager.getAll();
        assertEquals(5, addressTypeList.size());
    }

    @Test
    void testInsertAddressType() {

        String newAddressTypeName = "Headquarter";
        AddressType addressType = new AddressType(newAddressTypeName);
        int id = addressTypesManager.insert(addressType);

        AddressType insertedAddressType = addressTypesManager.getById(id);

        assertNotEquals(0, id);
        assertEquals("Headquarter", insertedAddressType.getName());
        assertNotNull(insertedAddressType.getCreatedDate());
    }

    @Test
    void UpdateAddressType() {

        String newAddressTypeName = "Main Office";
        int addressTypeId = 3;
        Date currentModifiedDate = null;

        AddressType addressTypeToUpdate = addressTypesManager.getById(addressTypeId);
        addressTypeToUpdate.setName(newAddressTypeName);
        currentModifiedDate = addressTypeToUpdate.getModifiedDate();

        addressTypesManager.update(addressTypeToUpdate);
        AddressType addressTypeUpdated = addressTypesManager.getById(addressTypeId);

        assertEquals(newAddressTypeName, addressTypeUpdated.getName());
        assertNotNull(addressTypeUpdated.getModifiedDate());
        if (currentModifiedDate != null) {
            assertTrue(addressTypeUpdated.getModifiedDate().compareTo(currentModifiedDate) > 0 );
        }
    }

    @Test
    void DeleteAddressType() {

        int addressTypeIdToDelete = 3;

        addressTypesManager.delete(addressTypesManager.getById(addressTypeIdToDelete));
        assertNull(addressTypesManager.getById(addressTypeIdToDelete));
    }
}