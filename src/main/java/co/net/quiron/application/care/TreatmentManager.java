package co.net.quiron.application.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the manager for the operations related to the patient treatments.
 */
@Data
public class TreatmentManager {

    private final Logger logger = LogManager.getLogger(this.getClass());

    IAppRepository<Treatment> treatmentRepository;
    AccountManager accountManager;
    Message message;


    /**
     * Instantiates a new Treatment manager.
     */
    public TreatmentManager() {
        message = new Message();
        treatmentRepository = RepositoryFactory.getDBContext(Treatment.class);
        logger.info("TreatmentManager(): Instantiation.");
    }

    /**
     * Instantiates a new Treatment manager.
     *
     * @param accountManager the account manager
     */
    public TreatmentManager(AccountManager accountManager) {
        this();
        this.accountManager =  accountManager;
        logger.info("TreatmentManager(AccountManager): Instantiation.");
    }

    /**
     * Gets patient treatment.
     *
     * @param treatmentId the treatment id
     * @return the patient visit
     */
    public Treatment getPatientTreatment(int treatmentId) {
        logger.info("getPatientTreatment(): Start.");
        Treatment treatment = treatmentRepository.get(treatmentId);

        if (treatment == null) {
            message.setType(MessageType.WARNING);
            message.setDescription("Treatment not found");
        }
        logger.info("getPatientTreatment(): end.");
        return treatment;
    }

    /**
     * Save treatment.
     *
     * @param treatment the visit
     * @return the boolean
     */
    public boolean saveTreatment (Treatment treatment) {

        logger.info("saveTreatment(): Start.");
        boolean success = false;

        try{
            treatmentRepository.update(treatment);
            logger.info("saveTreatment(): Success.");
            message = new Message(MessageType.INFO, "Treatment Information successfully updated.");
            success = true;
        }catch (Exception exception) {
            logger.error("saveTreatment(): Error: " + exception.getMessage());
            logger.trace("saveTreatment(): " + exception.getStackTrace());
            message = new Message(MessageType.ERROR, "Error saving the Treatment.");
        }

        logger.info("saveTreatment(): End.");
        return success;
    }

    /**
     * Gets treatments list.
     *
     * @return the patient treatments list
     */
    public List<Treatment> getTreatmentsList() {

        logger.info("getTreatmentsList(): Start.");
        List<Treatment> treatments;

        logger.debug("getTreatmentsList(): Getting the treatments list.");
        if(accountManager.getPersonType().equals("patient")) {
            Patient patient = (Patient)RepositoryFactory.getDBContext(Patient.class)
                    .get(accountManager.getId());
            treatments = treatmentRepository.getList()
                    .stream()
                    .filter(v -> v.getVisit()
                            .getPatient()
                            .equals(patient))
                    .collect(Collectors.toList());
        } else {
            Provider provider = (Provider)RepositoryFactory.getDBContext(Provider.class)
                    .get(accountManager.getId());
            treatments = treatmentRepository.getList()
                    .stream()
                    .filter(v -> v.getVisit()
                            .getProviderSchedule()
                            .getProvider()
                            .equals(provider))
                    .collect(Collectors.toList());
        }

        if (treatments.size() == 0) {
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
            logger.debug("getTreatmentsList(): Records not found.");
        }

        logger.info("getTreatmentsList(): End.");
        return treatments;
    }
}
