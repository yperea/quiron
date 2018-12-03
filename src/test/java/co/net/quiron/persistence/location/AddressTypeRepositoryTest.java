package co.net.quiron.persistence.location;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.location.AddressType;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressTypeRepositoryTest {

    IAppRepository<AddressType> addressTypeRepository;

    @BeforeEach
    void setUp() {
        addressTypeRepository = RepositoryFactory.getDBContext(AddressType.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting AddressType by its ID.
     */
    @Test
    void testGetAddressTypeById() {
        AddressType addressType = addressTypeRepository.get(1);
        assertEquals("Home", addressType.getName());
    }

    /**
     * Test the get all addressTypes.
     */
    @Test
    void testGetAllAddressTypes() {
        List<AddressType> addressTypeList = addressTypeRepository.getList();
        assertEquals(5, addressTypeList.size());
    }


    /**
     * Test the addressType creation.
     */
    @Test
    void testCreateAddressType() {

        String newAddressTypeName = "Main";
        AddressType newAddressType = new AddressType(newAddressTypeName);
        AddressType insertedAddressType = addressTypeRepository.create(newAddressType);

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

        AddressType addressTypeToUpdate = addressTypeRepository.get(addressTypeId);
        addressTypeToUpdate.setName(newAddressTypeName);

        addressTypeRepository.update(addressTypeToUpdate);
        AddressType addressTypeUpdated = addressTypeRepository.get(addressTypeId);

        assertEquals(newAddressTypeName, addressTypeUpdated.getName());
        assertNotNull(addressTypeUpdated.getModifiedDate());
    }

    /*TODO: Implement a Test Case for deleting an Addresstype with linked addresses */
    /**
     * Test the addressType deletion.
     */
    @Test
    void testDeleteAddressType() {
        int addressTypeIdToDelete = 6;
        String newAddressTypeName = "Main";
        AddressType newAddressType = new AddressType(newAddressTypeName);
        AddressType insertedAddressType = addressTypeRepository.create(newAddressType);
        addressTypeRepository.delete(insertedAddressType);
        assertNull(addressTypeRepository.get(addressTypeIdToDelete));
    }
}