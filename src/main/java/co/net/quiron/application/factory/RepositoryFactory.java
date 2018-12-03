package co.net.quiron.application.factory;

import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.persistence.shared.AppRepository;

/**
 * The type Repository factory.
 *
 * @param <T> the type parameter
 */
public class RepositoryFactory<T> {

    /**
     * Get db context app repository.
     *
     * @param entityType the entity type
     * @return the app repository
     */
    public static IAppRepository getDBContext(Class entityType){
        return new AppRepository(entityType);
    }
}
