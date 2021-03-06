package co.net.quiron.persistence.shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * An entity DAO based on Generic DAO Class provided by Paula Waite.
 *
 * @param <T> the type parameter
 */
public class EntityDAO<T> {
    private Class<T> type;
    private Session session;
    private Transaction transaction;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new BusinessEntity DAO.
     *
     * @param entityType the entity type
     */
    public EntityDAO(Class<T> entityType) {
        this.type = entityType;
        logger.trace("EntityDAO(Class<T>): Instantiating EntityDAO.");
    }

    /**
     * Gets an entity by id
     *
     * @param id entity id to search by
     * @return entity by id
     */
    public T getById(int id) {

        session = getSession();
        T entity = session.get(type, id);
        session.close();

        logger.trace("getById(int): Returning entity " + entity);
        return entity;
    }

    /**
     * Gets an entity by its composite key
     *
     * @param compositeKey entity key to search by
     * @return entity by composite key
     */
    public T getById(Map<String, Integer> compositeKey) {

        session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Predicate[] predicates  = getPredicates(compositeKey, builder, root);
        query.select(root).where(builder.and(predicates));

        T entity = session.createQuery(query).getResultList().stream().findFirst().orElse(null);
        session.close();

        logger.trace("getById(): Returning entity " + entity);
        return entity;
    }

    /**
     * Gets all the records
     *
     * @return the all entities
     */
    public List<T> getList() {
        session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();

        logger.trace("getAll(): Returning the List<T> of Entities.");
        return list;
    }

    /**
     * Get objects by params (exact match)
     *
     * @param params the key value parameter Map
     * @return the list of entities that meet params criteria
     */
    public List<T> getByPropertyEqual(Map<String, Object> params) {
        session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        List<Predicate> predicateList = new ArrayList<>();
        Predicate[] predicates;
        for (Map.Entry<String, Object> set : params.entrySet()) {
            predicateList.add(builder.equal(root.get(set.getKey()), set.getValue() ));
        }
        predicates  = predicateList.toArray(new Predicate[predicateList.size()]);

        query.select(root).where(predicates);

        List<T> list = session.createQuery( query ).getResultList();
        session.close();

        logger.debug("Searching for {} with {} = {}", type.getName(), params);
        return list;
    }

    /**
     * Get objects by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property equal
     */
    public List<T> getByPropertyEqual(String propertyName, String value) {
        session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> list = session.createQuery( query ).getResultList();
        session.close();

        logger.debug("Searching for {} with {} = {}", type.getName(), propertyName, value);
        return list;
    }

    /**
     * Get objects by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property like
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));
        List<T> list = session.createQuery( query ).getResultList();
        session.close();

        logger.debug("Searching for {} with {} like {}", type.getName(), propertyName, value);
        return list;
    }

    /**
     * Insert an BusinessEntity
     *
     * @param entity entity to be inserted or updated
     * @return id of the inserted record
     */
    public int insert(T entity) {
        int id = 0;
        session = getSession();
        transaction = session.beginTransaction();
        id = (int)session.save(entity);
        logger.trace("insert(T): Inserting the <T> BusinessEntity.");
        return id;
    }

    /**
     * Update an BusinessEntity
     *
     * @param entity BusinessEntity to be inserted or updated
     */
    public void update(T entity) {
        session = getSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        logger.trace("update(T): Updating the <T> BusinessEntity.");
    }

    /**
     * Deletes the entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        session = getSession();
        transaction = session.beginTransaction();
        session.delete(entity);
        logger.trace("delete(T): Deleting the <T> BusinessEntity.");
    }

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        logger.trace("getSession(): Opening Hibernate Session.");
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Commit changes to the Database and closes the session.
     */
    public void saveChanges() {
        transaction.commit();
        session.close();
        logger.trace("saveChanges(): Closing Hibernate Session.");
    }

    /**
     * Builds a predicates array for a QueryBuilder based on a key value parameters Map
     *
     * @param params parameters
     * @param builder CriteriaBuilder object
     * @param root ROOT<T> object
     * @return Array of Predicates
     */
    private Predicate[] getPredicates (Map<String, Integer> params, CriteriaBuilder builder, Root<T> root) {

        List<Predicate> predicateList = new ArrayList<>();
        Predicate[] predicates;
        for (Map.Entry<String, Integer> set : params.entrySet()) {
            predicateList.add(builder.equal(root.get(set.getKey()), set.getValue() ));
        }
        predicates  = predicateList.toArray(new Predicate[predicateList.size()]);
        return predicates;
    }
}
