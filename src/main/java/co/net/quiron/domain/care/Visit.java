package co.net.quiron.domain.care;

import co.net.quiron.domain.person.Patient;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents Visit domain for the application.
 *
 * @autor yperea
 */
@Entity(name = "Visit")
@Table(name = "VISITS")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Visit {

    @Id
    @Column(name = "VisitID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "ScheduledStartDate")
    private LocalDate scheduledStartDate;

    @NonNull
    @Column(name = "ScheduledEndDate")
    private LocalDate scheduledEndDate;


    @Column(name = "ActualStartDate")
    private LocalDate actualStartDate;

    @Column(name = "ActualEndDate")
    private LocalDate actualEndDate;

    @Column(name = "PatientBloodPressure")
    private Double bloodPressure;

    @Column(name = "PatientWeight")
    private Double weight;

    @Column(name = "PatientPulse")
    private Double pulse;

    @Column(name = "PatientSymptoms")
    private Double symptoms;

    @Column(name = "PatientComments")
    private Double patientComments;

    @Column(name = "ProviderComments")
    private Double providerComments;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ServiceID")
    private Service service;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;


    @NonNull
    @Column(name = "PostalCode")
    private String postalCode;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    /*TODO: Solve error when is FetchType.LAZY. org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... could not initialize proxy - no Session*/
    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Diagnostic> diagnostics = new HashSet<>();

    /*
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntityAddress> entityAddresses = new HashSet<>();
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


}
