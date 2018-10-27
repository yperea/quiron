package co.net.quiron.application.person;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.BusinessEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles Business entity operations.
 */
public class BusinessEntityManager extends EntityManager<BusinessEntity> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Business entity manager.
     */
    public BusinessEntityManager() {
        super(BusinessEntity.class);
        logger.info("BusinessEntityManager(): Instantiating BusinessEntityManager class.");

    }
}
