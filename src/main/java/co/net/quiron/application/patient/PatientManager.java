package co.net.quiron.application.patient;

import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Patient manager.
 */
public class PatientManager extends EntityManager<Patient> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Entities manager.
     */
    public PatientManager() {
        super(Patient.class);
        logger.info("PatientManager(): Instantiating PatientManager class.");
    }
}
