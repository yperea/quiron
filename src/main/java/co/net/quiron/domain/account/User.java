package co.net.quiron.domain.account;

import co.net.quiron.domain.person.Person;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the User domain for the application.
 *
 * @autor yperea
 */
@Entity(name = "User")
@Table(name = "USERS")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "Username")
    private String username;

    @NonNull
    @Column(name = "Email")
    private String email;

    @NonNull
    @Column(name = "Password")
    private String password;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "UserID")},
            inverseJoinColumns = {@JoinColumn(name = "RoleID")})
    private Set<Role> roles = new HashSet<>();


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERSON_USERS",
            joinColumns = {@JoinColumn(name = "UserID")},
            inverseJoinColumns = {@JoinColumn(name = "PersonID")})
    private Set<Person> persons = new HashSet<>();


/*
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private Set<Person> persons = new HashSet<>();
*/


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate; //@CreationTimestamp annotation only works with Date or Calendar types

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;


    /**
     * Add a role to the collection.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        roles.add(role);
    }

    /**
     * Remove a role from the collection.
     *
     * @param role the role
     */
    public void removeRole(Role role) {
        roles.remove(role);
    }

    /**
     * Add a person to the collection.
     *
     * @param person the person
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Remove a person from the collection.
     *
     * @param person the person
     */
    public void removePerson(Person person) {
        persons.remove(person);
    }

}
