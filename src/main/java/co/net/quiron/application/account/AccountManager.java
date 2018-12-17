package co.net.quiron.application.account;

import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.account.Profile;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the manager for the operations related to the user Account.
 */
@Data
public class AccountManager {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Profile profile;
    private Message message;

    private String personType;
    private String username;

    private boolean isSigned;
    private int userId;
    private int patientId;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String gender;

    private IAppRepository<User> userRepository;

/*  private IAppRepository<Patient> patientRepository;
    private IAppRepository<Provider> providerRepository;
    private IAppRepository<Person> personRepository;
    private IAppRepository<PersonType> personTypeRepository;
    private IAppRepository<Address> addressRepository;
*/

    /**
     * Instantiates a new Account manager.
     */
    public AccountManager(){
        profile = new Profile();
        userRepository = RepositoryFactory.getDBContext(User.class);

/*
        personRepository = RepositoryFactory.getDBContext(Person.class);
        patientRepository = RepositoryFactory.getDBContext(Patient.class);
        providerRepository = RepositoryFactory.getDBContext(Provider.class);
        personTypeRepository = RepositoryFactory.getDBContext(PersonType.class);
        addressRepository = RepositoryFactory.getDBContext(Address.class);
*/
    }

    /**
     * Instantiates an Account manager based on a username.
     *
     * @param username   the username
     * @param personType the person type
     */
    public AccountManager(String username, String personType) {
        this();
        this.username = username;
        this.personType = personType;
        loadProfile();
    }

    /**
     * Hanldes the Application Sign Up.
     *
     * @param type         the person type
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
    public boolean signUp(String type, int roleId, String firstName, String lastName,
                          String username, String email, String birthDate, String gender,
                          String password, String confirmation) {


        logger.info("signUp(): Start signUp.");

        boolean success = false;
        IAppRepository<Role> roleRepository = RepositoryFactory.getDBContext(Role.class);

        if (type == null) {
            type = "patient";
        }

        PersonType personType = (PersonType)RepositoryFactory.getDBContext(PersonType.class)
                                .getListEquals("name", type).get(0);

        logger.trace("signUp(): Create Patient.");
        Patient patient = (Patient)RepositoryFactory.getDBContext(Patient.class)
                          .create(new Patient(personType, firstName, lastName,
                                  LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/d/yyyy")),
                                  gender));

        logger.trace("signUp(): Create User.");
        User user = userRepository.create(new User(username, email, password));

        logger.trace("signUp(): Associating Patient to User.");
        user.addPerson(patient);

        logger.trace("signUp(): Associating Role to User.");
        user.addRole((Role)RepositoryFactory.getDBContext(Role.class).get(roleId));
        userRepository.update(user);

        if(user != null){
            success = true;
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
        } else {
            message = new Message(MessageType.ERROR, "Something went wrong during the process.");
        }

        logger.info("signUp(): End Signup.");
        return success;
    }


    /**
     * Load the User Profile.
     */
    public void loadProfile() {

        logger.info("loadUserAccount(String): Start loading account.");

        if(personType == null) {
            personType = "patient";
        }

        if(username != null)
        {
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
                    //profile.setAddress(person.getAddresses().stream().findFirst().get());
                    profile.setAddress(person.getAddresses().stream().findFirst().orElse(null));
                }
            } else {
                message = new Message(MessageType.ERROR, "No " + personType + " profile associated to the username provided.");
            }
        }
        logger.info("loadUserAccount(String): End loading account.");
    }

    /**
     * Save credentials.
     *
     * @param password     the password
     * @param confirmation the confirmation
     * @return the boolean
     */
    public boolean saveCredentials (String password, String confirmation) {

        logger.info("saveCredentials(): Start.");
        boolean success;

        User user = userRepository.get(userId);

        logger.trace("saveCredentials(): User. " + user);

        user.setPassword(password);
        userRepository.update(user);

        logger.info("saveCredentials(): Loading Account Information.");
        loadProfile();

        logger.info("saveCredentials(): End.");
        message = new Message(MessageType.INFO, "Account credentials successfully updated.");
        success = true;
        return success;
    }

    /**
     * Saves User's profile.
     *
     * @param profile the user profile
     * @return the operation result
     */
    public boolean saveProfile(Profile profile) {

        logger.info("saveProfile(): Start.");
        boolean success;
        Person person;

        if(personType.equals("patient")) {
             person = (Person) RepositoryFactory.getDBContext(Patient.class).get(id);
        } else {
             person = (Person) RepositoryFactory.getDBContext(Provider.class).get(id);
        }

        Address address = person.getAddresses().stream().findFirst().orElse(null);
        if(address == null) {
            address =  new Address();
            address.setAddressType(profile.getAddress().getAddressType());
            //provider.addAddress(address);
            person.addAddress(address);

        }

        person.setFirstName(profile.getFirstName());
        person.setLastName(profile.getLastName());

        if (profile.getNpi() != null) {
            ((Provider)person).setNpi(profile.getNpi());
        }

        if (profile.getBirthDate() != null) {
            ((Patient)person).setBirthDate(profile.getBirthDate());
        }

        if (profile.getGender() != null) {
            ((Patient)person).setGender(profile.getGender());
        }

        address.setAddressLine1(profile.getAddress().getAddressLine1());
        address.setAddressLine2(profile.getAddress().getAddressLine2());
        address.setCity(profile.getAddress().getCity());
        address.setState(profile.getAddress().getState());
        address.setPostalCode(profile.getAddress().getPostalCode());

        logger.info("saveProfile(): Updating Address Information.");
        RepositoryFactory.getDBContext(Address.class).update(address);
        logger.info("saveProfile(): Updating Person Information.");
        RepositoryFactory.getDBContext(Person.class).update(person);
        logger.info("saveProfile(): Loading Account Information.");
        loadProfile();

        logger.info("saveProfile(): End.");
        message = new Message(MessageType.INFO, "Account successfully updated.");
        success = true;
        return success;
    }


    /**
     * Saves Patient's Insurance Information.
     *
     * @param companyId      the insurance company id
     * @param subscriberCode the subscriber id
     * @return the profile
     */
    public boolean savePatientInsurance(int companyId, String subscriberCode) {

        logger.info("savePatientInsurance(): Start.");
        boolean success;

        Patient patient = (Patient)RepositoryFactory.getDBContext(Patient.class).get(id);
        Organization company = (Organization)RepositoryFactory.getDBContext(Organization.class).get(companyId);

        patient.setOrganization(company);
        patient.setSubscriberCode(subscriberCode);
        RepositoryFactory.getDBContext(Patient.class).update(patient);

        logger.info("getPatientProfile(String): Updating Profile Information.");
        profile.setPerson(patient);

        logger.info("saveProfile(): Loading Account Information.");
        loadProfile();

        logger.info("saveProfile(): End.");
        message = new Message(MessageType.INFO, "Insurance Information successfully updated.");
        success = true;
        return success;
    }

    /**
     * Gets account manager.
     *
     * @param session the session
     * @param request the request
     * @return the account manager
     */
    public static AccountManager getAccountManager(HttpSession session, HttpServletRequest request) {

        String username = null;
        String personType = "patient";

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){

            if (request.getUserPrincipal()!= null) {
                username = request.getUserPrincipal().getName();
            }

            if(session.getAttribute("personType") != null) {
                personType = (String) session.getAttribute("personType");
            }

            accountManager = new AccountManager(username, personType);
            if(accountManager != null) {
                session.setAttribute("message", accountManager.getMessage());
            }
            session.setAttribute("account", accountManager);
        }
        return accountManager;
    }
}
