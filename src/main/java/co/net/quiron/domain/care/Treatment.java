package co.net.quiron.domain.care;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "DiagnosticID")
    private Diagnostic diagnostic;

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
