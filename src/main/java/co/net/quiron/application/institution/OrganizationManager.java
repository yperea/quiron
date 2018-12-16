package co.net.quiron.application.institution;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.persistence.interfaces.IAppRepository;

import java.util.List;

/**
 * The type Organization manager.
 */
public class OrganizationManager {

    /**
     * Gets insurance companies.
     *
     * @return the insurance companies
     */
    public List<Organization> getInsuranceCompanies() {
        IAppRepository<Organization> organizationRepository = RepositoryFactory.getDBContext(Organization.class);
        return organizationRepository.getList();
    }
}
