package co.net.quiron.application.factory;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.Patient;

public class ManagerFactory<T> {

    //private Class<T> type;

/*    public ManagerFactory(Class<T> entityType) {
        this.type = entityType;
    }*/

/*    public EntityManager<T> getManager(T entityType) {

        this.type = (Class<T>) entityType;
        EntityManager<T> manager = new EntityManager<T>(type);

        //return new T EntityManager<>((Class<T>) entityType);

        return manager;

    }*/

    public static EntityManager getManager(Class entityType) {



        return new EntityManager(entityType);
/*
        if (entityType.getClass().isInstance(User.class)) {
            return new EntityManager(User.class);
        }
*/
        //String entityName = entityType.getSimpleName();

        //switch (entityName) {
        //    case "User": {
        //        return new EntityManager(entityType);
        //    }
        //}


        //return null;
    }


/*
    public ManagerFactory (Class<T> entityType){
        //entityRepository = new AppRepository<>(entityType);
        //logger.info("EntityManager(Class<T>): Instantiating EntityManager class.");
    }
*/

}
