package co.net.quiron.application.care;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Medication;
import co.net.quiron.persistence.interfaces.IAppRepository;

import java.util.List;

/**
 * The type Medication manager.
 */
public class MedicationManager {

    /**
     * Gets medications.
     *
     * @return the medications
     */
    public List<Medication> getMedications() {
        IAppRepository<Medication> medicationRepository = RepositoryFactory.getDBContext(Medication.class);
        List<Medication> medications = medicationRepository.getList();
        return medications;
    }
}
