package co.net.quiron.application.care;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Medication;
import co.net.quiron.persistence.interfaces.IAppRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Medication manager.
 */
public class MedicationManager {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets medications.
     *
     * @return the medications
     */
    public List<Medication> getMedications() {
        logger.info("getMedications().");
        IAppRepository<Medication> medicationRepository = RepositoryFactory.getDBContext(Medication.class);
        return medicationRepository.getList();
    }
}
