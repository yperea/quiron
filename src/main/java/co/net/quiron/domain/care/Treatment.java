package co.net.quiron.domain.care;

import co.net.quiron.domain.account.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Treatment")
@Table(name = "TREATMENTS")
public class Treatment {

    @Id
    @Column(name = "TreatmentID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "TREATMENTS_PRESCRIPTIONS",
            joinColumns = {@JoinColumn(name = "TreatmentID")},
            inverseJoinColumns = {@JoinColumn(name = "PrescriptionID")})
    private Set<Prescription> prescriptions = new HashSet<>();

    @NonNull
    @Column(name = "SymptomID")
    private int SymptomId;

    @NonNull
    @Column(name = "SymptomName")
    private String SymptomName;

    @NonNull
    @Column(name = "DiagnosticID")
    private int DiagnosticId;

    @NonNull
    @Column(name = "DiagnosticName")
    private String DiagnosticName;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "PatientComments")
    private String patientComments;

    @Column(name = "ProviderComments")
    private String providerComments;

    @Column(name = "Status")
    private String status;

    @Column(name = "Evaluation")
    private int evaluation;
}
