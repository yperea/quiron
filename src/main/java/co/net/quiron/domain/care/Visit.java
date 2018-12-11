package co.net.quiron.domain.care;

import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.schedule.ProviderSchedule;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents VisitForm domain for the application.
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
    private LocalDateTime scheduledStartDate;

    @Column(name = "ScheduledEndDate")
    private LocalDateTime scheduledEndDate;

    @NonNull
    @Column(name = "SymptomID")
    private int symptomId;

    @NonNull
    @Column(name = "SymptomName")
    private String symptomName;

    @NonNull
    @Column(name = "DiagnosticID")
    private int diagnosticId;

    @NonNull
    @Column(name = "DiagnosticName")
    private String diagnosticName;

    @Column(name = "ActualStartDate")
    private LocalDateTime actualStartDate;

    @Column(name = "ActualEndDate")
    private LocalDateTime actualEndDate;

    @Column(name = "PatientBloodPressure")
    private double patientBloodPressure;

    @Column(name = "PatientWeight")
    private double patientWeight;

    @Column(name = "PatientTemperature")
    private double patientTemperature;

    @Column(name = "PatientRespiration")
    private double patientRespiration;

    @Column(name = "PatientBMI")
    private double patientBMI;

    @Column(name = "PatientHeight")
    private double patientHeight;

    @Column(name = "PatientPulse")
    private double patientPulse;

    @Column(name = "PatientSymptoms")
    private String patientSymptoms;

    @Column(name = "ProviderComments")
    private String providerComments;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Treatment> treatments = new HashSet<>();

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
