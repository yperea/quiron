package co.net.quiron.domain.care;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents Medication domain for the application.
 */
@Entity(name = "Medication")
@Table(name = "MEDICATIONS")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Medication {
    @Id
    @Column(name = "MedicationID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "Name")
    private String name;

    @Column(name = "CommonName")
    private String commonName;

    @Column(name = "Description")
    private String description;

    @Column(name = "GeneralInstructions")
    private String instructions;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prescription> prescriptions = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

    @UpdateTimestamp
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}
