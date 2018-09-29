package co.net.quiron.persistence.shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.*;
import java.util.List;

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
     * Instantiates a new Entity DAO.
     *
     * @param entityType the entity type
     */
    public EntityDAO(Class<T> entityType) {
        this.type = entityType;
    }

    /**
     * Gets all the records
     *
     * @return the all entities
     */
    public List<T> getAll() {
        session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Gets an entity by id
     *
     * @param <T> the type parameter
     * @param id  entity id to search by
     * @return entity by id
     */
    public <T> T getById(int id) {
        session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Insert an Entity
     *
     * @param entity entity to be inserted or updated
     * @return id of the inserted record
     */
    public int insert(T entity) {
        int id = 0;
        session = getSession();
        transaction = session.beginTransaction();
        id = (int)session.save(entity);
        return id;
    }

    /**
     * Update an Entity
     *
     * @param entity Entity to be inserted or updated
     */
    public void update(T entity) {
        session = getSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
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
    }

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Commit changes to the Database and closes the session.
     */
    public void saveChanges() {
        transaction.commit();
        session.close();
    }
}
