package co.net.quiron.application.account;

import co.net.quiron.application.factory.RepositoryFactory;
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
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the manager for the operations related to the Account and user Profile.
 *
 * @Author Yesid Perea
 */
@Data
public class AccountManager {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Profile profile;
    private Message message;

    private boolean isSigned;
    private int userId;
    private int patientId;
    private int id;
    private String type;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String gender;

    private IAppRepository<User> userRepository;
    private IAppRepository<Role> roleRepository;
    private IAppRepository<Patient> patientRepository;
    private IAppRepository<Provider> providerRepository;
    private IAppRepository<PersonType> personTypeRepository;
    private IAppRepository<Address> addressRepository;

    /**
     * Instantiates a new Account manager.
     */
    public AccountManager(){
        profile = new Profile();

        patientRepository = RepositoryFactory.getDBContext(Patient.class);
        providerRepository = RepositoryFactory.getDBContext(Provider.class);
        personTypeRepository = RepositoryFactory.getDBContext(PersonType.class);
        userRepository = RepositoryFactory.getDBContext(User.class);
        roleRepository = RepositoryFactory.getDBContext(Role.class);
        addressRepository = RepositoryFactory.getDBContext(Address.class);
    }

    /**
     * Instantiates an Account manager based on a username.
     */
    public AccountManager(String username, String personType) {
        this();
        load(username, personType);
    }

    /**
     * Hanldes the Application Sign Up.
     *
     * @param type   the person type
     * @param roleId       the role id
     * @param firstName    the first name
     * @param lastName     the last name
     * @param username     the username
     * @param email        the email
     * @param birthDate    the birth date
     * @param gender       the gender
     * @param password     the password
     * @param confirmation the confirmation
     * @return the boolean
     */
    public boolean signup (String type, int roleId, String firstName, String lastName,
                           String username, String email, String birthDate, String gender,
                           String password, String confirmation) {


        logger.info("signup(): Start signup.");

        boolean isSignedUp = false;

        if (type == null) {
            type = "patient";
        }

        PersonType personType = personTypeRepository.getListEquals("name", type).get(0);

        logger.trace("signup(): Create Patient.");
        Patient patient = patientRepository.create(new Patient(personType, firstName, lastName,
                LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/d/yyyy")), gender));

        logger.trace("signup(): Create User.");
        User user = userRepository.create(new User(username, email, password));

        logger.trace("signup(): Associating Patient to User.");
        user.addPerson(patient);

        logger.trace("signup(): Associating Role to User.");
        user.addRole(roleRepository.get(roleId));
        userRepository.update(user);

        if(user != null){
            isSignedUp = true;
            this.id = patient.getId();
            this.isSigned = true;
            this.userId = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();

            profile.setFirstName(patient.getFirstName());
            profile.setLastName(patient.getLastName());
            profile.setPersonType(patient.getPersonType().getName().toLowerCase());
            profile.setBirthDate(patient.getBirthDate());
            profile.setGender(patient.getGender());
        }

        logger.info("signup(): End Signup.");
        return isSignedUp;
    }


    /**
     * Load the Profile.
     *
     * @param username the username
     * @param personType the type of person
     */
    public void load(String username, String personType) {

        logger.info("loadUserAccount(String): Start loading account.");

        if(personType == null) {
            personType = "patient";
        }

        User user = userRepository.getListEquals("username", username).get(0);
        String finalPersonType = personType;
        Person person = user.getPersons()
                .stream()
                .filter(p -> finalPersonType.equals(p.getPersonType().getName().toLowerCase()))
                .findAny()
                .orElse(null);

        if(person != null){
            this.isSigned = true;
            this.id = person.getId();
            this.userId = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();

            profile.setFirstName(person.getFirstName());
            profile.setLastName(person.getLastName());
            profile.setPersonType(person.getPersonType().getName().toLowerCase());
            
            if(personType.equals("patient")) {
                Patient patient = (Patient) RepositoryFactory.getDBContext(Patient.class).get(person.getId());
                profile.setBirthDate(patient.getBirthDate());
                profile.setGender(patient.getGender());
            }

            if(personType.equals("provider")) {
                Provider provider = (Provider) RepositoryFactory.getDBContext(Provider.class).get(person.getId());
                profile.setNpi(provider.getNpi());
            }

            if (person.getAddresses().size() > 0) {
                profile.setAddress(person.getAddresses().stream().findFirst().get());
            }
        }
        logger.info("loadUserAccount(String): End loading account.");
    }

    /**
     * Save credentials.
     *
     * @param password     the password
     * @param confirmation the confirmation
     */
    public void saveCredentials (String password, String confirmation) {

        logger.info("saveCredentials(): User.");
        User user = userRepository.get(userId);

        logger.trace("saveCredentials(): User. " + user);

        user.setPassword(password);
        userRepository.update(user);

        logger.info("saveCredentials(): End.");
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
        Patient patient = patientRepository.get(account.getPatientId());

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
            Provider provider = providerRepository.get(account.getPatientId());
            person = providerRepository.get(account.getId());
        } else {
            Patient patient = patientRepository.get(account.getPatientId());
            person = patientRepository.get(account.getId());
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
    public Profile saveProfile(AccountManager account, String firstName, String lastName,
                               String address1, String address2, String city, int stateId, String postalCode,
                               String birthDate, String gender) {


        logger.info("saveProfile(): Instantiating Managers.");

        AddressType addressType = (AddressType) RepositoryFactory.getDBContext(AddressType.class).get(3);
        State state = (State) RepositoryFactory.getDBContext(State.class).get(stateId);

        Patient patient = patientRepository.get(account.getId());
        Address address = patient.getAddresses().stream().findFirst().orElse(null);

        if(address == null) {
            address =  new Address();
            address.setAddressType(addressType);
            patient.addAddress(address);
        }

        address.setAddressLine1(address1);
        address.setAddressLine2(address2);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);
        addressRepository.update(address);

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setBirthDate(LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/d/yyyy")));
        patient.setGender(gender);
        patientRepository.update(patient);

        logger.info("saveProfile(): Calling Profile.setPerson().");
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

        AddressType addressType = (AddressType) RepositoryFactory.getDBContext(AddressType.class).get(3);
        State state = (State) RepositoryFactory.getDBContext(State.class).get(stateId);

        Provider provider = providerRepository.get(account.getId());

        Address address = provider.getAddresses().stream().findFirst().orElse(null);

        if(address == null) {
            address =  new Address();
            address.setAddressType(addressType);
            provider.addAddress(address);
        }

        provider.setFirstName(firstName);
        provider.setLastName(lastName);
        provider.setNpi(npi);

        address.setAddressLine1(address1);
        address.setAddressLine2(address2);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);

        addressRepository.update(address);
        providerRepository.update(provider);

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

        Patient patient = patientRepository.get(account.getId());
        Organization company = (Organization) RepositoryFactory.getDBContext(Organization.class).get(companyId);

        patient.setOrganization(company);
        patient.setSubscriberCode(subscriberCode);
        patientRepository.update(patient);

        logger.info("getPatientProfile(String): Calling Profile.setPatient().");
        profile.setPerson(patient);

        message = new Message(MessageType.INFO, "Insurance Information successfully updated.");
        return profile;
    }

}
