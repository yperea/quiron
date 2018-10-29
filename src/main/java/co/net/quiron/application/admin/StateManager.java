package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.location.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles State operations.
 */
public class StateManager extends EntityManager<State> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public StateManager() {
        super(State.class);
        logger.info("StateManager(): Instantiating StateManager class.");
    }

}
