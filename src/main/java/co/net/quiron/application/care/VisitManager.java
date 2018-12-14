package co.net.quiron.application.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Medication;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
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

    AccountManager accountManager;
    Message message;
    IAppRepository<Visit> visitRepository;
    Treatment treatment;

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

    /**
     * Add prescription boolean.
     *
     * @param medicationId       the medication id
     * @param treatmentStartDate the treatment start date
     * @param treatmentEndDate   the treatment end date
     * @param instructions       the instructions
     * @return the boolean
     */
    public boolean addTreatment(int visitId, int medicationId, LocalDate treatmentStartDate, LocalDate treatmentEndDate, String instructions) {
        boolean success = false;

        Visit visit = visitRepository.get(visitId);
        //Treatment newTreatment = new Treatment(visit);

        Medication medication = (Medication) RepositoryFactory.getDBContext(Medication.class).get(medicationId);
        Treatment newTreatment = visit.getTreatments().stream().findFirst().orElse(null);
        Prescription prescription = null;

        if(newTreatment != null) {
            RepositoryFactory.getDBContext(Treatment.class).delete(newTreatment);
            prescription = newTreatment.getPrescriptions().stream().findFirst().orElse(null);
        }

        //if(prescription != null) {
        //    RepositoryFactory.getDBContext(Prescription.class).delete(prescription);
        //}

        prescription = (Prescription) RepositoryFactory.getDBContext(Prescription.class)
                .create(new Prescription(instructions, medication));

        newTreatment =  new Treatment(visit);
        newTreatment.setStartDate(treatmentStartDate);
        newTreatment.setEndDate(treatmentEndDate);
        newTreatment.addPrescription(prescription);

        treatment = (Treatment) RepositoryFactory.getDBContext(Treatment.class).create(newTreatment);
        success = true;
        return success;
    }
}
