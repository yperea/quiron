package co.net.quiron.application.person;

import co.net.quiron.domain.person.PersonType;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTypeManagerTest {

    PersonTypeManager personTypeManager;

    @BeforeEach
    void setUp() {
        personTypeManager = new PersonTypeManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test getting PersonType by its ID.
     */
    @Test
    void testGetPersonTypeById() {
        PersonType personType = personTypeManager.get(1);
        assertEquals("Employee", personType.getName());
    }

    /**
     * Test the get all personTypes.
     */
    @Test
    void testGetAllPersonTypes() {
        List<PersonType> personTypeList = personTypeManager.getList();
        assertEquals(3, personTypeList.size());
    }


    /**
     * Test the personType creation.
     */
    @Test
    void testCreatePersonType() {

        String newPersonTypeName = "Doctor";
        PersonType newPersonType = new PersonType(newPersonTypeName);
        PersonType insertedPersonType = personTypeManager.create(newPersonType);

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

        PersonType personTypeToUpdate = personTypeManager.get(personTypeId);
        personTypeToUpdate.setName(newPersonTypeName);

        personTypeManager.update(personTypeToUpdate);
        PersonType personTypeUpdated = personTypeManager.get(personTypeId);

        assertEquals(personTypeToUpdate, personTypeUpdated);
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
        PersonType insertedPersonType = personTypeManager.create(newPersonType);

        //personTypeManager.delete(personTypeManager.get(personTypeIdToDelete));
        personTypeManager.delete(insertedPersonType);
        assertNull(personTypeManager.get(personTypeIdToDelete));
    }}