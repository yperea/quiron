package co.net.quiron.persistence.interfaces;

import java.util.List;
import java.util.Map;

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
    List<T> getList();

    /**
     * Get a collection of entities by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    List<T> getListEquals(String propertyName, String value);

    /**
     * Get a collection of entities by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    List<T> getListEquals(Map<String, Object> params);

    /**
     * Get a collection of entities by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    List<T> getListContains(String propertyName, String value);

    /**
     * Gets an entity.
     *
     * @param id the id
     * @return the t
     */
    T get(int id);

    /**
     * Gets an entity by its composite key
     *
     * @param compositeKey entity key to search by
     * @return entity by composite key
     */
    T get(Map<String, Integer> compositeKey);

    /**
     * Creates an entity.
     *
     * @param entity the entity
     * @return the int
     */
    T create(T entity);

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
