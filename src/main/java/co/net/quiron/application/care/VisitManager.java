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

    IAppRepository<Visit> visitRepository;
    AccountManager accountManager;
    Message message;
    Treatment treatment;

    /**
     * Instantiates a new Visit manager.
     */
    public VisitManager() {
        message = new Message();
        visitRepository = RepositoryFactory.getDBContext(Visit.class);
        logger.info("VisitManager(): Instantiation.");
    }

    /**
     * Instantiates a new Visit manager.
     *
     * @param accountManager the account manager
     */
    public VisitManager(AccountManager accountManager) {
        this();
        this.accountManager =  accountManager;
        logger.info("VisitManager(AccountManager): Instantiation.");
    }

    /**
     * Gets patient visit.
     *
     * @param visitId the visit id
     * @return the patient visit
     */
    public Visit getPatientVisit(int visitId) {
        logger.info("getPatientVisit(): Start.");
        Visit visit = visitRepository.get(visitId);

        if (visit == null) {
            message.setType(MessageType.WARNING);
            message.setDescription("Record not found");
        }
        logger.info("getPatientVisit(): End.");
        return visit;
    }

    /**
     * Save visit.
     *
     * @param visit the visit
     * @return the boolean
     */
    public boolean saveVisit (Visit visit) {

        logger.info("saveVisit(): Start.");
        boolean success = false;

        try{
            visitRepository.update(visit);
            logger.info("saveVisit(): Success.");
            message = new Message(MessageType.INFO, "Visit Information successfully updated.");
            success = true;
        }catch (Exception exception) {
            logger.error("saveVisit(): Error: " + exception.getMessage());
            logger.trace("saveVisit(): " + exception.getStackTrace());
            message = new Message(MessageType.ERROR, "Error saving the Visit.");
        }

        logger.info("saveVisit(): End.");
        return success;
    }

    /**
     * Gets patient visits list.
     *
     * @param state the state
     * @return the patient visits list
     */
    public List<Visit> getVisitsList(String state) {

        logger.info("getVisitsList(): Start.");
        if(state == null || state.isEmpty()) {
            state = "A";
        }
        String finalState = state;
        List<Visit> visits;

        logger.debug("getVisitsList(): Getting the visits list.");
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
            logger.debug("getVisitsList(): Records not found.");
        }
        logger.info("getVisitsList(): End.");
        return visits;
    }

    /**
     * Delete visit.
     *
     * @param visit the visit
     * @return the boolean
     */
    public boolean deleteVisit (Visit visit) {

        logger.info("deleteVisit(): Start.");
        boolean success = false;

        try{
            if (accountManager.getId() == visit.getPatient().getId()) {
                visitRepository.delete(visit);
                logger.info("deleteVisit(): Success.");
                message = new Message(MessageType.INFO, "Visit successfully deleted.");
                success = true;
            } else {
                logger.info("deleteVisit(): Illegal attempt.");
                message = new Message(MessageType.ERROR, "Illegal operation.");
            }

        }catch (Exception exception) {
            logger.error("deleteVisit(): Error: " + exception.getMessage());
            logger.trace("deleteVisit(): " + exception.getStackTrace());
            message = new Message(MessageType.ERROR, "Error deleting the Visit.");
        }

        logger.info("deleteVisit(): End.");
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
        logger.info("addTreatment(): Start.");
        boolean success;
        Visit visit = visitRepository.get(visitId);
        logger.debug("addTreatment(): Instantiate Medication object.");
        Medication medication = (Medication) RepositoryFactory.getDBContext(Medication.class)
                .get(medicationId);
        logger.debug("addTreatment(): Instantiate a new Treatment object.");
        Treatment newTreatment = visit.getTreatments().stream().findFirst().orElse(null);

        if(newTreatment != null) {
            RepositoryFactory.getDBContext(Treatment.class).delete(newTreatment); //This deletes also the prescriptions related
            //prescription = newTreatment.getPrescriptions().stream().findFirst().orElse(null);
        }

        logger.debug("addTreatment(): Instantiate a new Prescription object.");
        Prescription prescription = (Prescription) RepositoryFactory.getDBContext(Prescription.class)
                .create(new Prescription(instructions, medication));

        newTreatment =  new Treatment(visit);
        newTreatment.setStartDate(treatmentStartDate);
        newTreatment.setEndDate(treatmentEndDate);
        newTreatment.setStatus("A");
        newTreatment.addPrescription(prescription);

        try {
            logger.debug("addTreatment(): Create a new Treatment for the Visit.");
            treatment = (Treatment) RepositoryFactory.getDBContext(Treatment.class)
                    .create(newTreatment);
            message = new Message(MessageType.INFO, "Treatment successfully saved.");
            logger.info("Treatment successfully saved");
            success = true;
        }catch (Exception exception) {
            message = new Message(MessageType.ERROR, "Error creating Treatment.");
            logger.error("addTreatment(): Error: " + exception.getMessage());
            logger.trace("addTreatment(): " + exception.getStackTrace());
            success = false;
        }
        logger.info("addTreatment(): End.");
        return success;
    }

}
