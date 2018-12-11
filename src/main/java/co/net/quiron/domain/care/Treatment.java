package co.net.quiron.domain.care;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "VisitID")
    private Visit visit;

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

    /**
     * Add a prescription to the collection.
     *
     * @param prescription the prescription
     */
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    /**
     * Remove a prescription from the collection.
     *
     * @param prescription the prescription
     */
    public void removePrescription(Prescription prescription) {
        prescriptions.remove(prescription);
    }
}
