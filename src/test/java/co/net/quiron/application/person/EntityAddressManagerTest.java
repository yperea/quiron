package co.net.quiron.application.person;

import co.net.quiron.application.admin.AddressTypeManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.admin.AddressType;
import co.net.quiron.domain.person.Address;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.EntityAddress;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Entity address manager tester.
 */
class EntityAddressManagerTest {

    EntityManager<BusinessEntity> entityManager;
    EntityManager<Address> addressManager;
    EntityManager<AddressType> addressTypeManager;
    EntityManager<EntityAddress> entityAddressManager;

    /**
     * Initializes managers and data for the test.
     */
    @BeforeEach
    void setUp() {
        entityManager = new BusinessEntityManager();
        addressManager = new AddressManager();
        addressTypeManager = new AddressTypeManager();
        entityAddressManager = new EntityAddressManager();

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test create entity address.
     */
    @Test
    void testCreateEntityAddress() {

        BusinessEntity entity = entityManager.get(1);
        Address address = addressManager.get(1);
        AddressType addressType = addressTypeManager.get(1);

        EntityAddress newEntityAddress =  new EntityAddress(entity, address, addressType);

        EntityAddress createdEntityAddress = entityAddressManager.create2(newEntityAddress);

        assertNotNull(createdEntityAddress );
        assertEquals(address.getAddressLine1(), createdEntityAddress.getAddress().getAddressLine1());
    }
}