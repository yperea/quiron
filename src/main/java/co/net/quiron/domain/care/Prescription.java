package co.net.quiron.domain.care;

import co.net.quiron.domain.account.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents Prescription domain for the application.
 *
 * @autor yperea
 */
@Entity(name = "Prescription")
@Table(name = "PRESCRIPTIONS")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Prescription {

    @Id
    @Column(name = "PrescriptionID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "Instructions")
    private String instructions;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "prescriptions")
    private Set<Treatment> treatments = new HashSet<>();

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "MedicationID")
    private Medication medication;

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
