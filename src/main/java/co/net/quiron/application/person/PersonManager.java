package co.net.quiron.application.person;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles Person operations.
 */
public class PersonManager extends EntityManager<Person> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Person manager.
     */
    public PersonManager(){
        super(Person.class);
        logger.info("PersonManager(): Instantiating PersonManager class.");
    }
}
