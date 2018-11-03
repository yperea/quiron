package co.net.quiron.domain.person;

import co.net.quiron.domain.account.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the Person domain for the application.
 *
 * @autor yperea
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Person")
@Table(name = "PERSONS")
@PrimaryKeyJoinColumn(name = "PersonID")
public class Person extends BusinessEntity implements Serializable {


/*
    @Id
    @Column(name = "PersonID")
    protected int id;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "PersonID")
    protected BusinessEntity entity;
*/

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "PersonTypeID")
    protected PersonType personType;


/*
    @NonNull
    @Column(name = "PersonTypeID")
    protected int type;
*/

    /*
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERSON_USERS",
            joinColumns = {@JoinColumn(name = "PersonID")},
            inverseJoinColumns = {@JoinColumn(name = "UserID")})
    protected Set<Person> users = new HashSet<>();
    */

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "persons", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected Set<User> users = new HashSet<>();

    @Column(name = "Title")
    protected String title;

    @NonNull
    @Column(name = "FirstName")
    protected String firstName;

    @Column(name = "MiddleName")
    protected String middleName;

    @NonNull
    @Column(name = "LastName")
    protected String lastName;

    @Column(name = "LastName2")
    protected String lastName2;

    @Column(name = "Suffix")
    protected String suffix;


/*
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    protected Date createdDate;
*/

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    protected Date modifiedDate;

}
