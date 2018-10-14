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

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Person")
@Table(name = "PERSONS")
public class Person implements Serializable {

    @Id
    @Column(name = "PersonID")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "PersonID")
    private BusinessEntity entity;

    @NonNull
    @Column(name = "PersonTypeID")
    private int type;

    /*
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERSON_USERS",
            joinColumns = {@JoinColumn(name = "PersonID")},
            inverseJoinColumns = {@JoinColumn(name = "UserID")})
    private Set<Person> users = new HashSet<>();
    */

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "persons")
    private Set<User> users = new HashSet<>();

    @Column(name = "Title")
    private String title;

    @NonNull
    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "MiddleName")
    private String middleName;

    @NonNull
    @Column(name = "LastName")
    private String lastName;

    @Column(name = "LastName2")
    private String lastName2;

    @Column(name = "Suffix")
    private String suffix;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}
