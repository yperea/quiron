package co.net.quiron.domain.person;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "Patient")
@Table(name = "PATIENTS")
@PrimaryKeyJoinColumn(name = "PatientID")
public class Patient extends Person {

/*
    @Id
    @Column(name = "PatientID")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "PatientID")
    private Person person;
*/

    @NonNull
    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @NonNull
    @Column(name = "Gender")
    private String gender;

    @Column(name = "CompanyID")
    private int companyId;

    @Column(name = "SubscriberCode")
    private String subscriberCode;

    @Column(name = "IsPrimarySubscriber")
    private boolean isPrimarySubscriber;

    public Patient(PersonType personType, String firstName, String lastName, LocalDate birthDate, String gender) {
        super(personType, firstName, lastName);
        this.birthDate = birthDate;
        this.gender =  gender;
    }


/*
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;
*/

}
