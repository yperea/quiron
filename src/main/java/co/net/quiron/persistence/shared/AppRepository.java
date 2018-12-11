package co.net.quiron.persistence.shared;

import co.net.quiron.persistence.interfaces.IAppRepository;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<T> getList() {
        return entityDAO.getList();
    }

    @Override
    public List<T> getListEquals(Map<String, Object> params){
        return entityDAO.getByPropertyEqual(params);
    }

    @Override
    public List<T> getListEquals(String propertyName, String value) {
        return entityDAO.getByPropertyEqual(propertyName, value);
    }

    @Override
    public List<T> getListContains(String propertyName, String value) {
        return entityDAO.getByPropertyLike(propertyName, value);
    }

    @Override
    public T get(int id) {
        return entityDAO.getById(id);
    }

    @Override
    public T get(Map<String, Integer> compositeKey){
        return entityDAO.getById(compositeKey);
    }

    @Override
    public T create(T entity) {
        int i = entityDAO.insert(entity);
        entityDAO.saveChanges();
        return get(i);
    }

    @Override
    public void update(T entity) {
        entityDAO.update(entity);
        entityDAO.saveChanges();
    }

    @Override
    public void delete(T entity) {
        entityDAO.delete(entity);
        entityDAO.saveChanges();
    }
}
