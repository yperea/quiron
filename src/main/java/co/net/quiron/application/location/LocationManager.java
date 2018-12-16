package co.net.quiron.application.location;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.location.AddressType;
import co.net.quiron.domain.location.Country;
import co.net.quiron.domain.location.State;
import co.net.quiron.persistence.interfaces.IAppRepository;
import lombok.Data;

import java.util.Set;

/**
 * The type Location manager.
 */
@Data
public class LocationManager {

    /**
     * Gets states.
     *
     * @param countryCode the country code
     * @return the states
     */
    public Set<State> getStates(String countryCode) {

        IAppRepository<Country> countryRepository = RepositoryFactory.getDBContext(Country.class);

        return countryRepository
                .getListEquals("code", countryCode)
                .get(0)
                .getStates();
    }

    /**
     * Gets state.
     *
     * @param stateId the state id
     * @return the state
     */
    public State getState(int stateId) {

        IAppRepository<State> stateRepository = RepositoryFactory.getDBContext(State.class);
        return stateRepository.get(stateId);
    }


    /**
     * Gets address type.
     *
     * @param addressTypeId the address type id
     * @return the address type
     */
    public AddressType getAddressType(int addressTypeId) {
        IAppRepository<AddressType> addressTypeRepository = RepositoryFactory.getDBContext(AddressType.class);
        return addressTypeRepository.get(addressTypeId);
    }
}
