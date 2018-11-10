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
    public List<T> getAll() {
        return entityDAO.getAll();
    }

    @Override
    public List<T> getByPropertyEqual(Map<String, Object> params){
        return entityDAO.getByPropertyEqual(params);
    }

    @Override
    public List<T> getByPropertyEqual(String propertyName, String value) {
        return entityDAO.getByPropertyEqual(propertyName, value);
    }

    @Override
    public List<T> getByPropertyLike(String propertyName, String value) {
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

    //TODO: Refactoring create methods
    @Override
    public T create(T entity) {
        int i = entityDAO.insert(entity);
        entityDAO.saveChanges();
        return get(i);
    }

/*
    @Override
    public T create2(T entity) {
        //int i = entityDAO.insert(entity);
        String i = entityDAO.insert2(entity);
        entityDAO.saveChanges();
        return get(i);
    }
*/

    @Override
    public T create3(T entity) {
        T newEntity = entityDAO.insert3(entity);
        entityDAO.saveChanges();
        return newEntity;
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
