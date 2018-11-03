package co.net.quiron.application.person;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Business entity manager tester.
 */
class BusinessEntityManagerTest {

    EntityManager<BusinessEntity> businessEntityManager;
    EntityManager<Address> addressManager;

    /**
     * Initializes manager and data for the test.
     */
/*    @BeforeEach
    void setUp() {
        businessEntityManager = ManagerFactory.getManager(BusinessEntity.class);
        addressManager = ManagerFactory.getManager(Address.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }*/

    /**
     * Test get entity by id.
     */
/*    @Test
    void testGetEntityById() {
        BusinessEntity entity = businessEntityManager.get(1);
        int id = entity.getId();
        assertEquals(1, id);
    }*/

    /**
     * Test get all entities.
     */
/*    @Test
    void testGetAllEntities() {
        List<BusinessEntity> entityList = businessEntityManager.getList();
        assertEquals(4, entityList.size());
    }*/

    /**
     * Test create entity.
     */
/*    @Test
    void testCreateEntity() {

        BusinessEntity newEntity = new BusinessEntity();
        BusinessEntity insertedEntity = businessEntityManager.create(newEntity);

        assertNotNull(insertedEntity);
        assertEquals(5, insertedEntity.getId());
        assertNotNull(insertedEntity.getCreatedDate());
    }*/


    /**
     * Test adding set of addresses to existent businessEntity.
     */
/*    @Test
    void testAddingSetOfAddressesToAnExistentEntity() {
        int businessEntityId = 1;
        int addressId1 = 1;
        int addressId2 = 2;
        int addressesAssigned = 0;

        Set<Address> addresses = new HashSet<>();

        BusinessEntity businessEntityToUpdate = businessEntityManager.get(businessEntityId);
        Address address1 = addressManager.get(addressId1);
        Address address2 = addressManager.get(addressId2);

        addresses.add(address1);
        addresses.add(address2);

        businessEntityToUpdate.setAddresses(addresses);
        businessEntityManager.update(businessEntityToUpdate);

        BusinessEntity businessEntityUpdated = businessEntityManager.get(businessEntityId);

        addressesAssigned = businessEntityUpdated.getAddresses().size();

        assertEquals(2,addressesAssigned);
    }*/

    /**
     * Test adding address to existent businessEntity.
     */
/*
    @Test
    void testAddingOneAddressToAnExistentBusinessEntity() {
        int businessEntityId = 1;
        int addressId = 1;
        int addressesAssigned = 0;

        BusinessEntity businessEntityToUpdate = businessEntityManager.get(businessEntityId);
        Address address = addressManager.get(addressId);

        businessEntityToUpdate.addAddress(address);
        businessEntityManager.update(businessEntityToUpdate);

        BusinessEntity businessEntityUpdated = businessEntityManager.get(businessEntityId);
        addressesAssigned = businessEntityUpdated.getAddresses().size();
        Address newAddress = businessEntityUpdated.getAddresses().stream().findFirst().get();
        //Address newAddress = businessEntityUpdated.getAddresses().stream().max(Comparator.comparing(a -> a.getId())).get() ;
        //Address newAddress = businessEntityUpdated.getAddresses().stream().filter(address1 -> address1.equals(""));

        //Set<Address> addresses = (HashSet)businessEntityUpdated.getAddresses().stream().filter(address1 -> address1.equals(""));

        assertEquals(6,addressesAssigned);
        assertEquals(address, newAddress);
    }
*/

    /**
     * Test create businessEntity with one address.
     */
/*    @Test
    void testCreateBusinessEntityWithOneExistentAddress() {

        int addressId = 2; //Address
        int addressesAssigned = 0;

        BusinessEntity newBusinessEntity = new BusinessEntity();

        Address address = addressManager.get(addressId);
        newBusinessEntity.addAddress(address);

        BusinessEntity insertedBusinessEntity = businessEntityManager.create(newBusinessEntity);

        addressesAssigned = insertedBusinessEntity.getAddresses().size();
        Address newAddress = insertedBusinessEntity.getAddresses().stream().findFirst().get();

        assertNotNull(insertedBusinessEntity);
        assertNotNull(insertedBusinessEntity.getCreatedDate());
        assertEquals(1,addressesAssigned);
        assertEquals(address, newAddress);
    }
    */
    
}