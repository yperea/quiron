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
 * This class represents the manager for the operations related to the patient visits.
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
     * Gets patient visit.
     *
     * @param visitId the visit id
     * @return the patient visit
     */
    public Visit getPatientVisit(int visitId) {

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
        boolean success;

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
    public boolean addTreatment(int visitId, int medicationId,
                                LocalDate treatmentStartDate, LocalDate treatmentEndDate,
                                String instructions) {
        boolean success;
        Visit visit = visitRepository.get(visitId);
        Medication medication = (Medication) RepositoryFactory.getDBContext(Medication.class)
                .get(medicationId);
        Treatment newTreatment = visit.getTreatments().stream().findFirst().orElse(null);
        /*Prescription prescription = null;*/

        if(newTreatment != null) {
            RepositoryFactory.getDBContext(Treatment.class).delete(newTreatment); //This deletes also the prescriptions related
            //prescription = newTreatment.getPrescriptions().stream().findFirst().orElse(null);
        }

        Prescription prescription = (Prescription) RepositoryFactory.getDBContext(Prescription.class)
                .create(new Prescription(instructions, medication));

        newTreatment =  new Treatment(visit);
        newTreatment.setStartDate(treatmentStartDate);
        newTreatment.setEndDate(treatmentEndDate);
        newTreatment.addPrescription(prescription);

        treatment = (Treatment) RepositoryFactory.getDBContext(Treatment.class)
                .create(newTreatment);
        success = true;
        return success;
    }

    /**
     * Gets patient visits list.
     *
     * @param state the state
     * @return the patient visits list
     */
    public List<Visit> getVisitsList(String state) {

        if(state == null || state.isEmpty()) {
            state = "A";
        }
        String finalState = state;
        List<Visit> visits;

        if(accountManager.getPersonType().equals("patient")) {
            Map<String, Object> params = new TreeMap<>();
            Patient patient = (Patient) RepositoryFactory.getDBContext(Patient.class)
                    .get(accountManager.getId());
            params.put("patient", patient);
            visits = visitRepository.getListEquals(params)
                    .stream()
                    .filter(v -> finalState.equals(v.getStatus()))
                    .collect(Collectors.toList());
        } else {
            Provider provider = (Provider)RepositoryFactory.getDBContext(Provider.class)
                    .get(accountManager.getId());
            visits = visitRepository.getList()
                    .stream()
                    .filter(v -> finalState.equals(v.getStatus()))
                    .filter(ps -> ps.getProviderSchedule()
                            .getProvider()
                            .equals(provider))
                    .collect(Collectors.toList());
        }

        if (visits.size() == 0) {
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
        }

        return visits;
    }
}
