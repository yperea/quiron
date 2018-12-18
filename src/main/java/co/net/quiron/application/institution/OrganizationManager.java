package co.net.quiron.application.institution;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.persistence.interfaces.IAppRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Organization manager.
 */
public class OrganizationManager {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * Gets insurance companies.
     *
     * @return the insurance companies
     */
    public List<Organization> getInsuranceCompanies() {
        logger.info("getInsuranceCompanies().");
        IAppRepository<Organization> organizationRepository = RepositoryFactory.getDBContext(Organization.class);
        return organizationRepository.getList();
    }
}
