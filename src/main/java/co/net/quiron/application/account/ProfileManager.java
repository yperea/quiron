package co.net.quiron.application.account;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.Profile;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.location.AddressType;
import co.net.quiron.domain.location.State;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
import co.net.quiron.domain.person.PersonType;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Profile manager.
 */
@Data
public class ProfileManager {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Profile profile;
    private Message message;

    private EntityManager<User> userManager;
    private EntityManager<Role> roleManager;
    private EntityManager<Patient> patientManager;
    private EntityManager<Provider> providerManager;
    private EntityManager<PersonType> personTypeManager;
    private EntityManager<Address> addressManager;


    /**
     * Instantiates a new Profile manager.
     */
    public ProfileManager() {
        profile = new Profile();

        logger.info("ProfileManager(): Instantiating Managers.");
        patientManager = ManagerFactory.getManager(Patient.class);
        providerManager = ManagerFactory.getManager(Provider.class);
        personTypeManager = ManagerFactory.getManager(PersonType.class);
        userManager = ManagerFactory.getManager(User.class);
        roleManager = ManagerFactory.getManager(Role.class);
        addressManager = ManagerFactory.getManager(Address.class);
    }

    /*TODO: Implement support to others roles: Now this class only supports operations with Patients objects */

    /**
     * Gets patient Profile.
     *
     * @param account the username
     * @return the patient Profile
     */
    public Profile getPatientProfile(AccountManager account) {

        logger.info("getPatientProfile(String): Getting the Patient .");
        Patient patient = patientManager.get(account.getPatientId());

        if (patient != null) {
            logger.info("getPatientProfile(String): Calling Profile.setPatient().");
            profile.setPatient(patient);

            logger.info("getPatientProfile(String): Calling Profile.setAddress().");
            if (patient.getAddresses() != null && !patient.getAddresses().isEmpty()) {
                profile.setAddress(patient.getAddresses().stream().findFirst().get());
            }
        }


        logger.info("getPatientProfile(Person): Returning the Profile.");
        return profile;
    }

    /**
     * Gets patient Profile.
     *
     * @param account the username
     * @return the patient Profile
     */
    public Profile getProfile(AccountManager account, String profileType) {

        logger.info("getProfile(String): Getting the Profile.");

        Person person = new Person();

        if (profileType == "provider") {
            Provider provider = providerManager.get(account.getPatientId());
            person = providerManager.get(account.getId());
        } else {
            Patient patient = patientManager.get(account.getPatientId());
            person = patientManager.get(account.getId());
        }

       if (person  != null) {
            logger.info("getProfile(String): Calling Profile.setPatient().");
            //profile.setPatient(patient);
            profile.setPerson(person);

            logger.info("getPatientProfile(String): Calling Profile.setAddress().");
            if (person.getAddresses() != null && !person.getAddresses().isEmpty()) {
                profile.setAddress(person.getAddresses().stream().findFirst().get());
            }
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

        AddressType addressType = new AddressType();
        Patient patient = patientManager.get(account.getPatientId());

        Address address = new Address();

        if (!patient.getAddresses().isEmpty()) {
            address = patient.getAddresses().stream().findFirst().get();
        } else {
            addressType = (AddressType) ManagerFactory.getManager(AddressType.class).get(3);
            address.setAddressType(addressType);
            patient.addAddress(address);
        }

        State state = (State) ManagerFactory.getManager(State.class).get(stateId);

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

        message = new Message(MessageType.INFO, "Profile successfully updated.");

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
    public Profile saveProfile(AccountManager account, String firstName, String lastName,
                                      String address1, String address2, String city, int stateId, String postalCode,
                                      String birthDate, String gender) {


        logger.info("saveProfile(): Instantiating Managers.");

        AddressType addressType = new AddressType();
        Patient patient = patientManager.get(account.getId());

        Address address = new Address();

        if (!patient.getAddresses().isEmpty()) {
            address = patient.getAddresses().stream().findFirst().get();
        } else {
            addressType = (AddressType) ManagerFactory.getManager(AddressType.class).get(3);
            address.setAddressType(addressType);
            patient.addAddress(address);
        }

        State state = (State) ManagerFactory.getManager(State.class).get(stateId);

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

        logger.info("saveProfile(): Calling Profile.setPatient().");
        //profile.setPatient(patient);
        profile.setPerson(patient);

        logger.info("saveProfile(): Calling Profile.setAddress().");
        profile.setAddress(address);

        message = new Message(MessageType.INFO, "Profile successfully updated.");

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
     * @param npi        the provider NPI
     * @return the profile
     */
    public Profile saveProfile(AccountManager account, String firstName, String lastName,
                               String address1, String address2, String city, int stateId, String postalCode,
                               String npi) {


        logger.info("saveProfile(): Instantiating Managers.");

        AddressType addressType = new AddressType();
        Provider provider = providerManager.get(account.getId());

        Address address = new Address();

        if (!provider.getAddresses().isEmpty()) {
            address = provider.getAddresses().stream().findFirst().get();
        } else {
            addressType = (AddressType) ManagerFactory.getManager(AddressType.class).get(3);
            address.setAddressType(addressType);
            provider.addAddress(address);
        }

        State state = (State) ManagerFactory.getManager(State.class).get(stateId);

        provider.setFirstName(firstName);
        provider.setLastName(lastName);
        provider.setNpi(npi);


        //address.setAddressType(addressType);
        address.setAddressLine1(address1);
        address.setAddressLine2(address2);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);

        addressManager.update(address);
        providerManager.update(provider);

        logger.info("saveProfile(): Calling Profile.setPatient().");
        profile.setProvider(provider);
        profile.setPerson(provider);

        logger.info("saveProfile(): Calling Profile.setAddress().");
        profile.setAddress(address);

        message = new Message(MessageType.INFO, "Profile successfully updated.");

        return profile;
    }

    /**
     * Saves Patient's Insurance Information.
     *
     * @param account    the account
     * @param companyId  the insurance company id
     * @param subscriberCode the subscriber id
     * @return the profile
     */
    public Profile savePatientInsurance(AccountManager account, int companyId, String subscriberCode) {


        logger.info("savePatientInsurance(): Instantiating Managers.");

        Patient patient = patientManager.get(account.getId());
        Organization company = (Organization) ManagerFactory.getManager(Organization.class).get(companyId);

        patient.setOrganization(company);
        patient.setSubscriberCode(subscriberCode);
        patientManager.update(patient);

        logger.info("getPatientProfile(String): Calling Profile.setPatient().");
        profile.setPerson(patient);

        message = new Message(MessageType.INFO, "Insurance Information successfully updated.");
        return profile;
    }

}
