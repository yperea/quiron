package co.net.quiron.application.account;

import co.net.quiron.application.admin.RoleManager;
import co.net.quiron.application.admin.UserManager;
import co.net.quiron.application.person.BusinessEntityManager;
import co.net.quiron.application.person.PersonManager;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.account.User;
import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.Person;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Account manager.
 */
@Data
public class AccountManager {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private User user;
    private Person person;
    private boolean isSigned;


    /**
     * Sign Up.
     *
     * @param personTypeId the person type id
     * @param roleId       the role id
     * @param firstName    the first name
     * @param lastName     the last name
     * @param username     the username
     * @param email        the email
     * @param password     the password
     * @param confirmation the confirmation
     * @return the boolean
     */
    public boolean signup (int personTypeId, int roleId, String firstName, String lastName,
                          String username, String email,
                          String password, String confirmation) {


        logger.info("signup(): Start signup.");

        boolean isSignedUp = false;

        UserManager userManager = new UserManager();
        RoleManager roleManager = new RoleManager();

        EntityManager<BusinessEntity> businessEntityManager = new BusinessEntityManager();
        EntityManager<Person> personManager = new PersonManager();

        logger.trace("signup(): Create User.");
        this.user = userManager.create(new User(username, email, password));

        /*TODO: Try to integrate businessEntityManager inside Person object*/
        Person person = new Person(personTypeId, firstName, lastName);
        person.setEntity(businessEntityManager.create(new BusinessEntity()));

        logger.trace("signup(): Create Person.");
        this.person = personManager.create(person);

        logger.trace("signup(): Associating Person to User.");
        this.user.addPerson(this.person);

        logger.trace("signup(): Associating Role to User.");
        this.user.addRole(roleManager.get(roleId));
        userManager.update(this.user);


        if(this.user != null){
            isSignedUp = true;
        }

        logger.info("signup(): End Signup.");

        return isSignedUp;
    }

    /**
     * Load user account.
     *
     * @param username the username
     */
    public void loadUserAccount(String username) {

        UserManager userManager = new UserManager();

        logger.info("loadUserAccount(String): Start loading account.");

        this.user = userManager.getListEquals("username", username).get(0);
        this.person = this.user.getPersons().stream().findFirst().get();

        logger.info("loadUserAccount(String): End loading account.");
    }
}
