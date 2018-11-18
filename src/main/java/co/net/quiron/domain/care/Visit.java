package co.net.quiron.domain.care;

import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.schedule.ProviderSchedule;
import co.net.quiron.domain.schedule.ShiftSchedule;
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
 * This class represents MyVisit domain for the application.
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="ProviderID", referencedColumnName = "ProviderID"),
            @JoinColumn(name="ShiftID", referencedColumnName = "ShiftID"),
            @JoinColumn(name="WeekDayID", referencedColumnName = "WeekDayID"),
            @JoinColumn(name="OrganizationID", referencedColumnName = "OrganizationID"),
            @JoinColumn(name="LocationID", referencedColumnName = "LocationID")
    })
    private ProviderSchedule providerSchedule;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ServiceID")
    private Service service;

    @NonNull
    @Column(name = "Status")
    private String status;

    @Column(name = "ScheduledStartDate")
    private LocalDate scheduledStartDate;

    @Column(name = "ScheduledEndDate")
    private LocalDate scheduledEndDate;

    @Column(name = "ActualStartDate")
    private LocalDate actualStartDate;

    @Column(name = "ActualEndDate")
    private LocalDate actualEndDate;

    @Column(name = "PatientBloodPressure")
    private double patientBloodPressure;

    @Column(name = "PatientWeight")
    private double patientWeight;

    @Column(name = "PatientTemperature")
    private double patientTemperature;

    @Column(name = "PatientRespiration")
    private double patientRespiration;

    @Column(name = "PatientBMI")
    private double patientBmi;

    @Column(name = "PatientHeight")
    private double patientHeight;

    @Column(name = "PatientPulse")
    private double patientPulse;

    @Column(name = "PatientSymptoms")
    private String patientSymptoms;

    @Column(name = "ProviderComments")
    private String providerComments;

    /*TODO: Solve error when is FetchType.LAZY. org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... could not initialize proxy - no Session*/
    /*
    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Diagnostic> diagnostics = new HashSet<>();
    */

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
