package co.net.quiron.application.shared;

import co.net.quiron.persistence.shared.AppRepository;
import co.net.quiron.persistence.interfaces.IAppRepository;
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
        logger.info("EntityManager(Class<T>): Instantiating EntityManager class.");
    }

    /**
     * Gets the list of all of the Entities.
     *
     * @return the list of entities
     */
    public List<T> getList(){
        List<T> entities = entityRepository.getAll();

        logger.debug("getList(): Returning list of Entities.");
        logger.trace("getList(): Returning " + entities);

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

        logger.debug("get(int): Returning Entity.");
        logger.trace("get(int): Returning " + entity);

        return entity;
    }

    /**
     * Creates a new Entity.
     *
     * @param entity the entity to be created
     * @return the entity just created
     */
    public T create (T entity) {

        logger.debug("create(T): Creating Entity.");
        logger.trace("create(T): Creating " + entity);

        return entityRepository.create(entity);
    }

    /**
     * Updates a specific Entity.
     *
     * @param entity the entity to be updated
     */
    public void update (T entity) {

        logger.debug("update(T): Updating Entity.");
        logger.trace("update(T): Updating " + entity);

        entityRepository.update(entity);
    }

    /**
     * Deletes a specific Entity.
     *
     * @param entity the entity to be deleted
     */
    public void delete (T entity) {

        logger.debug("delete(T): Deleting Entity.");
        logger.trace("delete(T): Deleting " + entity);

        entityRepository.delete(entity);
    }
    
}
