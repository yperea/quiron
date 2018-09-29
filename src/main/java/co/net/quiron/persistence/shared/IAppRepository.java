package co.net.quiron.persistence.shared;

import java.util.List;

/**
 * The interface for controllers accept a reference to any object that implements it.
 *
 * @param <T> the type parameter
 * @author yperea
 */
public interface IAppRepository<T> {

    /**
     * Gets all the entities.
     *
     * @return the all
     */
    List<T> getAll();

    /**
     * Gets an entity.
     *
     * @param id the id
     * @return the t
     */
    T get(int id);

    /**
     * Creates an entity.
     *
     * @param entity the entity
     * @return the int
     */
    int create(T entity);

    /**
     * Deletes an entity.
     *
     * @param entity the entity
     */
    void delete(T entity);

    /**
     * Updates an entity.
     *
     * @param entity the entity
     */
    void update(T entity);
}
