package co.net.quiron.application.person;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.Person;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Business entity manager tester.
 */
class BusinessEntityManagerTest {

    EntityManager<BusinessEntity> businessEntityManager;

    /**
     * Initializes manager and data for the test.
     */
    @BeforeEach
    void setUp() {
        businessEntityManager = new BusinessEntityManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get entity by id.
     */
    @Test
    void testGetEntityById() {
        BusinessEntity entity = businessEntityManager.get(1);
        int id = entity.getId();
        assertEquals(1, id);
    }

    /**
     * Test get all entities.
     */
    @Test
    void testGetAllEntities() {
        List<BusinessEntity> entityList = businessEntityManager.getList();
        assertEquals(2, entityList.size());
    }

    /**
     * Test create entity.
     */
    @Test
    void testCreateEntity() {

        BusinessEntity newEntity = new BusinessEntity();
        BusinessEntity insertedEntity = businessEntityManager.create(newEntity);

        assertNotNull(insertedEntity);
        assertEquals(3, insertedEntity.getId());
        assertNotNull(insertedEntity.getCreatedDate());
    }

}