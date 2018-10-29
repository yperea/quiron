package co.net.quiron.application.account;

import co.net.quiron.application.patient.PatientManager;
import co.net.quiron.application.person.BusinessEntityManager;
import co.net.quiron.application.person.PersonManager;
import co.net.quiron.application.person.PersonTypeManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
import co.net.quiron.domain.person.PersonType;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Account manager.
 */
@Data
public class AccountManager {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private boolean isSigned;
    private int userId;
    private int patientId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

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

        UserManager userManager = new UserManager();
        RoleManager roleManager = new RoleManager();
        PersonTypeManager personTypeManager = new PersonTypeManager();
        PersonManager personManager = new PersonManager();
        PatientManager patientManager = new PatientManager();

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
        }

        logger.info("signup(): End Signup.");
        return isSignedUp;
    }

    /**
     * Load the User Account.
     *
     * @param username the username
     */
    public void loadUserAccount(String username) {

        UserManager userManager = new UserManager();
        PatientManager patientManager = new PatientManager();

        logger.info("loadUserAccount(String): Start loading account.");

        User user = userManager.getListEquals("username", username).get(0);
        Person person = user.getPersons().stream().findFirst().get();

        if(user != null){
            this.isSigned = true;
            this.userId = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.firstName = person.getFirstName();
            this.lastName = person.getLastName();
            this.patientId = person.getId();
        }

        logger.info("loadUserAccount(String): End loading account.");
    }

    public void saveCredentials (String password, String confirmation) {

        UserManager userManager = new UserManager();

        logger.info("saveCredentials(): User.");
        User user = userManager.get(userId);

        logger.trace("saveCredentials(): User. " + user);

        user.setPassword(password);
        userManager.update(user);

        logger.info("saveCredentials(): End.");
    }
}
