package co.net.quiron.persistence.shared;

import java.util.List;

/**
 * This Class implements a repository for the application that acts as an abstraction layer between
 * the data access layer and the business logic layer of the application.
 *
 * @param <T> the type parameter
 * @author yperea
 */
public class AppRepository<T> implements IAppRepository<T> {

    private EntityDAO<T> entityDAO;

    /**
     * Instantiates a new App repository.
     *
     * @param entityType the entity type
     */
    public AppRepository(Class<T> entityType) {

        entityDAO = new EntityDAO<>(entityType);
    }

    /**
     * Gets all the records
     *
     * @return the all entities
     */
    @Override
    public List<T> getAll() {
        return entityDAO.getAll();
    }

    /**
     * Gets an entity by id
     *
     * @param id  entity id to search by
     * @return entity by id
     */
    @Override
    public T get(int id) {
        return entityDAO.getById(id);
    }

    /**
     * Creates an BusinessEntity in the database
     *
     * @param entity entity to be inserted or updated
     * @return id of the inserted record
     */
    /*@Override
    public int create(T entity) {

        int i = entityDAO.insert(entity);
        entityDAO.saveChanges();

        return i;
    }*/

    /**
     * Creates an BusinessEntity in the database
     *
     * @param entity entity to be inserted or updated
     * @return the inserted record
     */
    @Override
    public T create(T entity) {
        int i = entityDAO.insert(entity);
        entityDAO.saveChanges();
        return get(i);
    }

    /**
     * Updates an BusinessEntity
     *
     * @param entity BusinessEntity to be inserted or updated
     */
    @Override
    public void update(T entity) {
        entityDAO.update(entity);
        entityDAO.saveChanges();
    }

    /**
     * Deletes an entity.
     *
     * @param entity entity to be deleted
     */
    @Override
    public void delete(T entity) {
        entityDAO.delete(entity);
        entityDAO.saveChanges();
    }
}
