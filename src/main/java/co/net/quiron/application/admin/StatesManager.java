package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.Country;
import co.net.quiron.domain.admin.State;
import co.net.quiron.persistence.shared.AppRepository;
import co.net.quiron.persistence.shared.IAppRepository;

import java.util.List;

public class StatesManager {
    private IAppRepository<State> stateRepository;
    private Country country;

    public StatesManager() {
        stateRepository = new AppRepository<>(State.class);
    }

    /**
     * Gets the list of all of the States.
     *
     * @return the list of states
     */
    public List<State> getStateList(){
        return stateRepository.getAll();
    }

    /**
     * Gets a specific state by its id.
     *
     * @param id the id
     * @return the state
     */
    public State getState(int id) {
        return stateRepository.get(id);
    }

    /**
     * Creates a new State.
     *
     * @param state the state to be created
     * @return the state just created
     */
    public State create (State state) {
        return stateRepository.create(state);
    }

    /**
     * Updates a specific State.
     *
     * @param state the state to be updated
     */
    public void update (State state) {
        stateRepository.update(state);
    }

    /**
     * Deletes a specific State.
     *
     * @param state the state to be deleted
     */
    public void delete (State state) {
        stateRepository.delete(state);
    }
    
}
