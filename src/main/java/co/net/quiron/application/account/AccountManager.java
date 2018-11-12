package co.net.quiron.application.account;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.Role;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
import co.net.quiron.domain.person.PersonType;
import co.net.quiron.util.Message;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Account manager.
 */
@Data
public class AccountManager {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private boolean isSigned;
    private int userId;
    private int patientId;
    private int id;
    private String type;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Message message;
    private EntityManager<User> userManager;
    private EntityManager<Role> roleManager;
    private EntityManager<Patient> patientManager;
    private EntityManager<PersonType> personTypeManager;

    /**
     * Instantiates a new Account manager.
     */
    public AccountManager(){
        patientManager = ManagerFactory.getManager(Patient.class);
        personTypeManager = ManagerFactory.getManager(PersonType.class);
        userManager = ManagerFactory.getManager(User.class);
        roleManager = ManagerFactory.getManager(Role.class);
    }


    /**
     * Hanldes the Application Sign Up.
     *
     * @param personTypeId the person type id
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
    public boolean signup (int personTypeId, int roleId, String firstName, String lastName,
                          String username, String email, String birthDate, String gender,
                          String password, String confirmation) {


        logger.info("signup(): Start signup.");


        boolean isSignedUp = false;

        PersonType personType = personTypeManager.get(personTypeId);

        logger.trace("signup(): Create Person.");
        Patient patient = patientManager.create(new Patient(personType, firstName, lastName,
                LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/d/yyyy")), gender));

        logger.trace("signup(): Create User.");
        User user = userManager.create(new User(username, email, password));

        logger.trace("signup(): Associating Person to User.");
        user.addPerson(patient);

        logger.trace("signup(): Associating Role to User.");
        user.addRole(roleManager.get(roleId));
        userManager.update(user);

        if(user != null){
            isSignedUp = true;
            this.isSigned = true;
            this.userId = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.firstName = patient.getFirstName();
            this.lastName = patient.getLastName();
            this.patientId = patient.getId();
            this.id = patient.getId();
            this.type = patient.getPersonType().getName().toLowerCase();
        }

        logger.info("signup(): End Signup.");
        return isSignedUp;
    }

    /**
     * Load the User Account.
     *
     * @param username the username
     */
    public void loadUserAccount(String username, String personType) {

        logger.info("loadUserAccount(String): Start loading account.");
        //String finalPersonType = personType;

        User user = userManager.getListEquals("username", username).get(0);
        Person person = user.getPersons()
                .stream()
                .filter(p -> personType.equals(p.getPersonType().getName().toLowerCase()))
                .findAny()
                .orElse(null);

        if(person != null){
            this.isSigned = true;
            this.userId = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.firstName = person.getFirstName();
            this.lastName = person.getLastName();
            //this.patientId = person.getId();
            this.id = person.getId();
            this.type = person.getPersonType().getName().toLowerCase();

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
        User user = userManager.get(userId);

        logger.trace("saveCredentials(): User. " + user);

        user.setPassword(password);
        userManager.update(user);

        logger.info("saveCredentials(): End.");
    }
}
