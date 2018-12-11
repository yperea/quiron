package co.net.quiron.domain.person;

import co.net.quiron.domain.institution.Organization;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "Patient")
@Table(name = "PATIENTS")
@PrimaryKeyJoinColumn(name = "PatientID")
public class Patient extends Person {

    @NonNull
    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @NonNull
    @Column(name = "Gender")
    private String gender;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "CompanyID")
    private Organization organization;

    @Column(name = "SubscriberCode")
    private String subscriberCode;

    @Column(name = "IsPrimarySubscriber")
    private boolean isPrimarySubscriber;

    public Patient(PersonType personType, String firstName, String lastName, LocalDate birthDate, String gender) {
        super(personType, firstName, lastName);
        this.birthDate = birthDate;
        this.gender =  gender;
    }
}
