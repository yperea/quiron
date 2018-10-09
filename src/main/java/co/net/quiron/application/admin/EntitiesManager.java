package co.net.quiron.application.admin;

import co.net.quiron.persistence.shared.AppRepository;
import co.net.quiron.persistence.shared.IAppRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class EntitiesManager<T> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private IAppRepository<T> entityRepository;

    /**
     * Instantiates a new Entities manager.
     */
    public EntitiesManager(Class<T> entityType) {
        entityRepository = new AppRepository<>(entityType);
        logger.info("EntitiesManager(): Instantiating EntitiesManager class.");
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

        logger.debug("getEntity(): Returning Entity.");
        logger.trace("getEntity(): Returning " + entity);

        return entity;
    }

    /**
     * Creates a new Entity.
     *
     * @param entity the entity to be created
     * @return the entity just created
     */
    public T create (T entity) {

        logger.debug("create(Entity): Creating Entity.");
        logger.trace("create(Entity): Creating " + entity);

        return entityRepository.create(entity);
    }

    /**
     * Updates a specific Entity.
     *
     * @param entity the entity to be updated
     */
    public void update (T entity) {

        logger.debug("update(Entity): Updating Entity.");
        logger.trace("update(Entity): Updating " + entity);

        entityRepository.update(entity);
    }

    /**
     * Deletes a specific Entity.
     *
     * @param entity the entity to be deleted
     */
    public void delete (T entity) {

        logger.debug("delete(Entity): Deleting Entity.");
        logger.trace("delete(Entity): Deleting " + entity);

        entityRepository.delete(entity);
    }
    
}
