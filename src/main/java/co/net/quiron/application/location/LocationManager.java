package co.net.quiron.application.location;

import co.net.quiron.application.factory.RepositoryFactory;
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
        Set<State> states = countryRepository
                            .getListEquals("code", countryCode)
                            .get(0)
                            .getStates();
        return states;
    }
}
