package co.net.quiron.application.admin;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.admin.State;
import co.net.quiron.persistence.shared.IAppRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Holds the business logic to handle the States.
 */
public class StateManager extends EntityManager<State> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private IAppRepository<State> stateRepository;

    public StateManager() {
        super(State.class);
        //stateRepository = new AppRepository<>(State.class);
        logger.info("StateManager(): Instantiating StateManager class.");
    }

    /**
     * Gets the list of all of the States.
     *
     * @return the list of states
     */
    /*
    public List<State> getStateList(){
        List<State> states = stateRepository.getAll();

        logger.debug("getStateList(): Returning list of States.");
        logger.trace("getStateList(): Returning " + states);

        return states;
    }
    */

    /**
     * Gets a specific state by its id.
     *
     * @param id the id
     * @return the state
     */
    /*
    public State getState(int id) {
        State state = stateRepository.get(id);

        logger.debug("getState(): Returning State.");
        logger.trace("getState(): Returning " + state);

        return state;

    }
    */

    /**
     * Creates a new State.
     *
     * @param state the state to be created
     * @return the state just created
     */
    /*
    public State create (State state) {
        logger.debug("create(State): Creating State.");
        logger.trace("create(State): Creating " + state);

        return stateRepository.create(state);

    }
    */

    /**
     * Updates a specific State.
     *
     * @param state the state to be updated
     */
    /*
    public void update (State state) {
        logger.debug("update(State): Updating State.");
        logger.trace("update(State): Updating " + state);

        stateRepository.update(state);

    }
    */

    /**
     * Deletes a specific State.
     *
     * @param state the state to be deleted
     */
    /*
    public void delete (State state) {
        logger.debug("delete(State): Deleting State.");
        logger.trace("delete(State): Deleting " + state);

        stateRepository.delete(state);

    }
    */

}
