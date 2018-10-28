package co.net.quiron.application.patient;

import co.net.quiron.application.account.UserManager;
import co.net.quiron.application.admin.AddressTypeManager;
import co.net.quiron.application.admin.CountryManager;
import co.net.quiron.application.admin.StateManager;
import co.net.quiron.application.person.AddressManager;
import co.net.quiron.application.person.BusinessEntityManager;
import co.net.quiron.application.person.PersonManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.admin.AddressType;
import co.net.quiron.domain.admin.Country;
import co.net.quiron.domain.admin.State;
import co.net.quiron.domain.person.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Set;

/**
 * Handles Patient entity operations.
 */
public class PatientManager extends EntityManager<Patient> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Profile profile;

    /**
     * Instantiates a new Entities manager.
     */
    public PatientManager() {
        super(Patient.class);
        logger.info("PatientManager(): Instantiating PatientManager class.");
    }

    /**
     * Gets patient Profile.
     *
     * @param username the username
     * @return the patient Profile
     */
    public Profile getPatientProfile(String username) {

        logger.info("getPatientProfile(Person): Instantiating Managers.");

        UserManager userManager = new UserManager();
        AddressManager addressManager = new AddressManager();
        PatientManager patientManager = new PatientManager();
        BusinessEntityManager entityManager = new BusinessEntityManager();
        profile = new Profile();

        logger.info("getPatientProfile(String): Getting the User account.");
        User user = userManager.getListEquals("username", username).get(0);

        logger.info("getPatientProfile(String): Getting the Person .");
        Person person = user.getPersons().stream().findFirst().get();

        logger.info("getPatientProfile(String): Calling Profile.setPerson().");
        profile.setPerson(person);
        logger.info("getPatientProfile(String): Calling Profile.setPatient().");
        profile.setPatient(patientManager.get(person.getId()));
        logger.info("getPatientProfile(String): Calling Profile.setAddress().");

        if (!entityManager.get(person.getId()).getAddresses().isEmpty()) {
            profile.setAddress(entityManager.get(person.getId()).getAddresses().stream().findFirst().get());
        }

        logger.info("getPatientProfile(Person): Returning the Profile.");

        return profile;
    }

    public Profile saveProfile(int profileId, String firstName, String lastName, String address1, String address2,
                               String city, int stateId, String postalCode) {


        BusinessEntityManager entityManager = new BusinessEntityManager();
        PersonManager personManager = new PersonManager();
        AddressManager addressManager = new AddressManager();
        PatientManager patientManager = new PatientManager();

        profile = new Profile();

        Address address = new Address();
        AddressType addressType = new AddressTypeManager().get(3);
        State state = new StateManager().get(stateId);

        Person person = personManager.get(profileId);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        personManager.update(person);

        address.setAddressType(addressType);
        address.setAddressLine1(address1);
        address.setAddressLine2(address2);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);

        if (entityManager.get(person.getId()).getAddresses().isEmpty()) {
            address = addressManager.create(address);
        } else {
            addressManager.update(address);
        }

        BusinessEntity businessEntityToUpdate = entityManager.get(profileId);
        businessEntityToUpdate.addAddress(address);
        entityManager.update(businessEntityToUpdate);


        logger.info("getPatientProfile(String): Calling Profile.setPerson().");
        profile.setPerson(personManager.get(profileId));
        logger.info("getPatientProfile(String): Calling Profile.setPatient().");
        profile.setPatient(patientManager.get(person.getId()));
        logger.info("getPatientProfile(String): Calling Profile.setAddress().");
        profile.setAddress(address);

        return profile;
    }
}
