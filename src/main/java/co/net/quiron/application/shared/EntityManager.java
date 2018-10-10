package co.net.quiron.application.shared;

import co.net.quiron.persistence.shared.AppRepository;
import co.net.quiron.persistence.shared.IAppRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class EntityManager<T> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private IAppRepository<T> entityRepository;

    /**
     * Instantiates a new Entities manager.
     */
    public EntityManager(Class<T> entityType) {
        entityRepository = new AppRepository<>(entityType);
        logger.info("EntityManager(): Instantiating EntityManager class.");
    }

    /**
     * Gets the list of all of the Entities.
     *
     * @return the list of entities
     */
    public List<T> getList(){
        List<T> entities = entityRepository.getAll();

        logger.debug("getEntityList(): Returning list of Entities.");
        logger.trace("getEntityList(): Returning " + entities);

        return entities;
    }

    /**
     * Gets a specific entity by its id.
     *
     * @param id the id
     * @return the entity
     */
    public T get(int id) {
        T entity = entityRepository.get(id);

        logger.debug("getEntity(): Returning BusinessEntity.");
        logger.trace("getEntity(): Returning " + entity);

        return entity;
    }

    /**
     * Creates a new BusinessEntity.
     *
     * @param entity the entity to be created
     * @return the entity just created
     */
    public T create (T entity) {

        logger.debug("create(BusinessEntity): Creating BusinessEntity.");
        logger.trace("create(BusinessEntity): Creating " + entity);

        return entityRepository.create(entity);
    }

    /**
     * Updates a specific BusinessEntity.
     *
     * @param entity the entity to be updated
     */
    public void update (T entity) {

        logger.debug("update(BusinessEntity): Updating BusinessEntity.");
        logger.trace("update(BusinessEntity): Updating " + entity);

        entityRepository.update(entity);
    }

    /**
     * Deletes a specific BusinessEntity.
     *
     * @param entity the entity to be deleted
     */
    public void delete (T entity) {

        logger.debug("delete(BusinessEntity): Deleting BusinessEntity.");
        logger.trace("delete(BusinessEntity): Deleting " + entity);

        entityRepository.delete(entity);
    }
    
}
