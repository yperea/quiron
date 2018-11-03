package co.net.quiron.application.person;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.PersonType;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProviderManagerTest {

    EntityManager<Provider> providerManager;
    EntityManager<Address> addressManager;
    EntityManager<PersonType> personTypeManager;

    /**
     * Initializes managers and data for the test.
     */
    @BeforeEach
    void setUp() {

        providerManager = ManagerFactory.getManager(Provider.class);
        addressManager = ManagerFactory.getManager(Address.class);
        personTypeManager = ManagerFactory.getManager(PersonType.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");

    }

    /**
     * Tests get provider by id.
     */
    @Test
    void testGetProviderById() {
        Provider provider = providerManager.get(3);
        String firstName = provider.getFirstName();
        assertEquals("Yesid", firstName);
    }

    /**
     * Tests get all providers.
     */
    @Test
    void testGetAllProviders() {
        List<Provider> providerList = providerManager.getList();
        assertEquals(2, providerList.size());
    }

    /**
     * Tests create provider.
     */
    @Test
    void testCreateProvider() {

        LocalDate birthDate = LocalDate.parse("1968-01-01");
        String gender = "M";
        String firstName = "John";
        String lastName = "Smith";
        String npi = "123465";
        PersonType personType = personTypeManager.get(2);


        Provider provider = new Provider(personType, firstName, lastName, npi);
        Provider insertedProvider = providerManager.create(provider);

        assertNotNull(insertedProvider);
        assertEquals("John", insertedProvider.getFirstName());
        assertEquals("Smith", insertedProvider.getLastName());
        assertEquals("123465", insertedProvider.getNpi());
        assertNotNull(insertedProvider.getCreatedDate());
    }


    /**
     * Test Load user account.
     */
/*
    @Test
    void testGetProviderProfile() {

        String userName = "yesper";
        Profile profile = providerManager.getProviderProfile(userName);


*/
/*
        Person person = personManager.get(1);
        Address address = addressManager.get(1);

        Person personProfile = profile.getPerson();
        Address addressProfile = profile.getAddress();


        assertEquals(person, personProfile);
        assertEquals(address, addressProfile);
*//*


    }
*/

}