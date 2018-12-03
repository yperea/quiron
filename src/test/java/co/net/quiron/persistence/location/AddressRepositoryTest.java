package co.net.quiron.persistence.location;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.location.AddressType;
import co.net.quiron.domain.location.State;
import co.net.quiron.domain.location.Address;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Address repository tester.
 */
class AddressRepositoryTest {

    IAppRepository<Address> addressRepository;
    IAppRepository<State> stateRepository;
    IAppRepository<AddressType> addressTypeRepository;

    /**
     * Initializes repositorys and data for the test.
     */
    @BeforeEach
    void setUp() {
        addressRepository = RepositoryFactory.getDBContext(Address.class);
        stateRepository = RepositoryFactory.getDBContext(State.class);
        addressTypeRepository = RepositoryFactory.getDBContext(AddressType.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get address by id.
     */
    @Test
    void testGetAddressByID() {
        Address address = addressRepository.get(1);

        assertNotNull(address);
        assertEquals("675 W. Washington Ave.", address.getAddressLine1());
    }

    /**
     * Test get all addresses.
     */
    @Test
    void testGetAllAddresses() {
        List<Address> addressList = addressRepository.getList();
        assertEquals(11,addressList.size());
    }

    /**
     * Test create address.
     */
    @Test
    void testCreateAddress() {

        State state = stateRepository.get(6);
        AddressType addressType = addressTypeRepository.get(3);

        String address1 = "211 North Carroll Street";
        String city = "Madison";
        String postalCode = "53703";

        Address newAddress = new Address(address1, city, state, addressType, postalCode);
        Address createAddress = addressRepository.create(newAddress);

        assertNotNull(createAddress);
        assertEquals(newAddress.getAddressLine1(), createAddress.getAddressLine1());
    }

    /**
     * Test update address.
     */
    @Test
    void testUpdateAddress() {

        Address address = addressRepository.get(1);
        State state = stateRepository.get(5);
        AddressType addressType = addressTypeRepository.get(1);
        String address1 = "2125 Commercial Avenue";
        address.setAddressLine1(address1);
        address.setState(state);
        address.setAddressType(addressType);
        addressRepository.update(address);
        Address updatedAddress = addressRepository.get(1);

        assertEquals(address1, updatedAddress.getAddressLine1());
    }

    /*TODO: Fix Delete address test*/
    /**
     * Test delete address.
     */
    @Test
    void testDeleteAddress() {

        State state = stateRepository.get(6);
        AddressType addressType = addressTypeRepository.get(3);

        String address1 = "211 North Carroll Street";
        String city = "Madison";
        String postalCode = "53703";

        Address newAddress = new Address(address1, city, state, addressType, postalCode);
        Address createAddress = addressRepository.create(newAddress);

        int idToDelete = createAddress.getId();

        addressRepository.delete(addressRepository.get(idToDelete));
        assertNull(addressRepository.get(idToDelete));
    }

}