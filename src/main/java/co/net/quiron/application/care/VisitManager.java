package co.net.quiron.application.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Visit;
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
 * The type Visit manager.
 */
@Data
public class VisitManager {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Account manager.
     */
    AccountManager accountManager;
    /**
     * The Message.
     */
    Message message;
    /**
     * The Visit repository.
     */
    IAppRepository<Visit> visitRepository;

    /**
     * Instantiates a new Visit manager.
     */
    public VisitManager() {
        message = new Message();
        visitRepository = RepositoryFactory.getDBContext(Visit.class);
    }

    /**
     * Instantiates a new Visit manager.
     *
     * @param accountManager the account manager
     */
    public VisitManager(AccountManager accountManager) {
        this();
        this.accountManager =  accountManager;
    }

    /**
     * Gets patient visits list.
     *
     * @param state the state
     * @return the patient visits list
     */
    public List<Visit> getPatientVisitsList(String state) {

        IAppRepository<Patient> patientRepository = RepositoryFactory.getDBContext(Patient.class);
        Map<String, Object> params = new TreeMap<>();
        int patientId = accountManager.getId();
        Patient patient = patientRepository.get(patientId);
        params.put("patient", patient);

        List<Visit> visits = visitRepository.getListEquals(params)
                .stream()
                .filter(v -> state.equals(v.getStatus()))
                .collect(Collectors.toList());

        if (visits.size() == 0) {
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
        }

        return visits;
    }

    /**
     * Gets provider visits list.
     *
     * @param state the state
     * @return the provider visits list
     */
    public List<Visit> getProviderVisitsList(String state) {

        IAppRepository<Provider> providerRepository = RepositoryFactory.getDBContext(Provider.class);
        Map<String, Object> params = new TreeMap<>();
        int providerId = accountManager.getId();
        Provider provider = providerRepository.get(providerId);
        params.put("provider", provider);

        List<Visit> visits = visitRepository.getList()
                .stream()
                .filter(v -> state.equals(v.getStatus()))
                .filter(ps -> ps.getProviderSchedule()
                        .getProvider()
                        .equals(provider))
                .collect(Collectors.toList());

        if (visits.size() == 0) {
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
        }

        return visits;
    }

    /**
     * Gets patient visit.
     *
     * @param visitId the visit id
     * @return the patient visit
     */
    public Visit getPatientVisit(int visitId) {

        IAppRepository<Patient> patientRepository = RepositoryFactory.getDBContext(Patient.class);
        Map<String, Object> params = new TreeMap<>();
        int patientId = accountManager.getId();
        Patient patient = patientRepository.get(patientId);
        params.put("patient", patient);

        Visit visit = visitRepository.get(visitId);

        if (visit == null) {
            message.setType(MessageType.WARNING);
            message.setDescription("Record not found");
        }

        return visit;
    }

    /**
     * Save visit boolean.
     *
     * @param visit the visit
     * @return the boolean
     */
    public boolean saveVisit (Visit visit) {

        logger.info("saveVisit(): Start.");
        boolean success =  false;

        visitRepository.update(visit);

        logger.info("saveVisit(): End.");
        message = new Message(MessageType.INFO, "Visit Information successfully updated.");

        success = true;
        return success;
    }
}
