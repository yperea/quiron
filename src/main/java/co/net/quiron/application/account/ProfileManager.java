package co.net.quiron.application.account;

import co.net.quiron.application.admin.AddressTypeManager;
import co.net.quiron.application.admin.StateManager;
import co.net.quiron.application.patient.PatientManager;
import co.net.quiron.application.person.AddressManager;
import co.net.quiron.domain.account.Profile;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.location.AddressType;
import co.net.quiron.domain.location.State;
import co.net.quiron.domain.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Profile manager.
 */
public class ProfileManager {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Profile profile;

    /**
     * Instantiates a new Profile manager.
     */
    public ProfileManager() {
        profile = new Profile();
    }

    /*TODO: Implement support to others roles: Now this class only supports operations with Patients objects */

    /**
     * Gets patient Profile.
     *
     * @param account the username
     * @return the patient Profile
     */
    public Profile getPatientProfile(AccountManager account) {

        logger.info("getProfile(Person): Instantiating Managers.");
        PatientManager patientManager = new PatientManager();

        logger.info("getPatientProfile(String): Getting the Patient .");
        Patient patient = patientManager.get(account.getPatientId());

        logger.info("getPatientProfile(String): Calling Profile.setPatient().");
        profile.setPatient(patient);

        logger.info("getPatientProfile(String): Calling Profile.setAddress().");
        if (!patient.getAddresses().isEmpty()) {
            profile.setAddress(patient.getAddresses().stream().findFirst().get());
        }

        logger.info("getPatientProfile(Person): Returning the Profile.");
        return profile;
    }

    /**
     * Saves Patient's profile.
     *
     * @param account    the account
     * @param firstName  the first name
     * @param lastName   the last name
     * @param address1   the address 1
     * @param address2   the address 2
     * @param city       the city
     * @param stateId    the state id
     * @param postalCode the postal code
     * @param birthDate  the birth date
     * @param gender     the gender
     * @return the profile
     */
    public Profile savePatientProfile(AccountManager account, String firstName, String lastName,
                                      String address1, String address2, String city, int stateId, String postalCode,
                                      String birthDate, String gender) {


        logger.info("savePatientProfile(): Instantiating Managers.");
        AddressManager addressManager = new AddressManager();
        PatientManager patientManager = new PatientManager();
        AddressType addressType = new AddressType();
        Patient patient = patientManager.get(account.getPatientId());

        Address address = new Address();

        if (!patient.getAddresses().isEmpty()) {
            address = patient.getAddresses().stream().findFirst().get();
        } else {
            addressType = new AddressTypeManager().get(3);
            address.setAddressType(addressType);
            patient.addAddress(address);
        }

        State state = new StateManager().get(stateId);

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setBirthDate(LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/d/yyyy")));
        patient.setGender(gender);


        //address.setAddressType(addressType);
        address.setAddressLine1(address1);
        address.setAddressLine2(address2);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);

        addressManager.update(address);
        patientManager.update(patient);

        logger.info("getPatientProfile(String): Calling Profile.setPatient().");
        profile.setPatient(patient);
        logger.info("getPatientProfile(String): Calling Profile.setAddress().");
        profile.setAddress(address);

        return profile;
    }
}
