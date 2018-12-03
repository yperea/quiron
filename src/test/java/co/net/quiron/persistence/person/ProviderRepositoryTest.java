package co.net.quiron.persistence.person;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.PersonType;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProviderRepositoryTest {

    IAppRepository<Provider> providerRepository;
    IAppRepository<Address> addressRepository;
    IAppRepository<PersonType> personTypeRepository;

    /**
     * Initializes repositorys and data for the test.
     */
    @BeforeEach
    void setUp() {

        providerRepository = RepositoryFactory.getDBContext(Provider.class);
        addressRepository = RepositoryFactory.getDBContext(Address.class);
        personTypeRepository = RepositoryFactory.getDBContext(PersonType.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");

    }

    /**
     * Tests get provider by id.
     */
    @Test
    void testGetProviderById() {
        Provider provider = providerRepository.get(3);
        String firstName = provider.getFirstName();
        assertEquals("Yesid", firstName);
    }

    /**
     * Tests get all providers.
     */
    @Test
    void testGetAllProviders() {
        List<Provider> providerList = providerRepository.getList();
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
        PersonType personType = personTypeRepository.get(2);


        Provider provider = new Provider(personType, firstName, lastName, npi);
        Provider insertedProvider = providerRepository.create(provider);

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
        Profile profile = providerRepository.getProviderProfile(userName);


*/
/*
        Person person = personRepository.get(1);
        Address address = addressRepository.get(1);

        Person personProfile = profile.getPerson();
        Address addressProfile = profile.getAddress();


        assertEquals(person, personProfile);
        assertEquals(address, addressProfile);
*//*


    }
*/

}