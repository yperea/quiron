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
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * This class represents the manager for the operations related to the patient treatments.
 */
@Data
public class TreatmentManager {

    private final Logger logger = LogManager.getLogger(this.getClass());

    Message message;
    AccountManager accountManager;
    IAppRepository<Treatment> treatmentRepository;

    /**
     * Instantiates a new Treatment manager.
     */
    public TreatmentManager() {
        message = new Message();
        treatmentRepository = RepositoryFactory.getDBContext(Treatment.class);
    }

    /**
     * Instantiates a new Treatment manager.
     *
     * @param accountManager the account manager
     */
    public TreatmentManager(AccountManager accountManager) {
        this();
        this.accountManager =  accountManager;
    }

    /**
     * Gets patient treatment.
     *
     * @param treatmentId the treatment id
     * @return the patient visit
     */
    public Treatment getPatientTreatment(int treatmentId) {

        IAppRepository<Patient> patientRepository = RepositoryFactory.getDBContext(Patient.class);
        Map<String, Object> params = new TreeMap<>();
        int patientId = accountManager.getId();
        Patient patient = patientRepository.get(patientId);
        params.put("patient", patient);

        Treatment treatment = treatmentRepository.get(treatmentId);

        if (treatment == null) {
            message.setType(MessageType.WARNING);
            message.setDescription("Treatment not found");
        }
        return treatment;
    }

    /**
     * Save treatment boolean.
     *
     * @param treatment the visit
     * @return the boolean
     */
    public boolean saveTreatment (Treatment treatment) {

        logger.info("saveTreatment(): Start.");
        boolean success =  false;

        treatmentRepository.update(treatment);

        logger.info("saveTreatment(): End.");
        message = new Message(MessageType.INFO, "Treatment Information successfully updated.");

        success = true;
        return success;
    }

    /**
     * Gets treatments list.
     *
     * @return the patient treatments list
     */
    public List<Treatment> getTreatmentsList() {

        List<Treatment> treatments;

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
        }
        return treatments;
    }
}
