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

@Data
public class AccountManager {

    private User user;
    private Person person;
    private boolean isSigned;


    public boolean signup (int personTypeId, int roleId, String firstName, String lastName,
                          String userName, String email,
                          String password, String confirmation) {

        boolean isSignedUp = false;

        UserManager userManager = new UserManager();
        RoleManager roleManager = new RoleManager();

        EntityManager<BusinessEntity> businessEntityManager = new BusinessEntityManager();
        EntityManager<Person> personManager = new PersonManager();

        this.user = userManager.create(new User(userName, email, password));

        /*TODO: Try to integrate businessEntityManager inside Person object*/
        Person person = new Person(personTypeId, firstName, lastName);
        person.setEntity(businessEntityManager.create(new BusinessEntity()));

        this.person = personManager.create(person);

        this.user.addPerson(this.person);
        this.user.addRole(roleManager.get(roleId));
        userManager.update(this.user);

        if(this.user != null){
            isSignedUp = true;
        }

        return isSignedUp;
    }
}
