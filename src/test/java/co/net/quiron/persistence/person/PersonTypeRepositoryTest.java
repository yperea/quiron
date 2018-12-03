package co.net.quiron.persistence.person;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.person.PersonType;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTypeRepositoryTest {

    IAppRepository<PersonType> personTypeRepository;

    @BeforeEach
    void setUp() {
        personTypeRepository = RepositoryFactory.getDBContext(PersonType.class);
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting PersonType by its ID.
     */
    @Test
    void testGetPersonTypeById() {
        PersonType personType = personTypeRepository.get(1);
        assertEquals("Employee", personType.getName());
    }

    /**
     * Test the get all personTypes.
     */
    @Test
    void testGetAllPersonTypes() {
        List<PersonType> personTypeList = personTypeRepository.getList();
        assertEquals(3, personTypeList.size());
    }


    /**
     * Test the personType creation.
     */
    @Test
    void testCreatePersonType() {

        String newPersonTypeName = "Doctor";
        PersonType newPersonType = new PersonType(newPersonTypeName);
        PersonType insertedPersonType = personTypeRepository.create(newPersonType);

        assertNotNull(insertedPersonType);
        assertEquals("Doctor", insertedPersonType.getName());
        assertNotNull(insertedPersonType.getCreatedDate());
    }

    /**
     * Test the personType update.
     */
    @Test
    void testUpdatePersonType() {

        String newPersonTypeName = "Physician";
        int personTypeId = 2;

        PersonType personTypeToUpdate = personTypeRepository.get(personTypeId);
        personTypeToUpdate.setName(newPersonTypeName);

        personTypeRepository.update(personTypeToUpdate);
        PersonType personTypeUpdated = personTypeRepository.get(personTypeId);

        assertEquals(newPersonTypeName, personTypeUpdated.getName());
        assertNotNull(personTypeUpdated.getModifiedDate());
    }

    /*TODO: Implement a Test Case for deleting an PersonType with linked people */
    /**
     * Test the personType deletion.
     */
    @Test
    void testDeletePersonType() {

        int personTypeIdToDelete = 4;

        String newPersonTypeName = "Doctor";
        PersonType newPersonType = new PersonType(newPersonTypeName);
        PersonType insertedPersonType = personTypeRepository.create(newPersonType);

        //personTypeRepository.delete(personTypeRepository.get(personTypeIdToDelete));
        personTypeRepository.delete(insertedPersonType);
        assertNull(personTypeRepository.get(personTypeIdToDelete));
    }}