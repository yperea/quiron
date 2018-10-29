package co.net.quiron.application.person;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.PersonType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles Person Type operations.
 */
public class PersonTypeManager extends EntityManager<PersonType> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Person type manager.
     */
    public PersonTypeManager() {
        super(PersonType.class);
        logger.info("PersonTypeManager(): Instantiating PersonTypeManager class.");
    }

}
