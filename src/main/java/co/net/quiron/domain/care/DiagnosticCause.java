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
 * This class represents Diagnostic Cause domain for the application.
 *
 * @autor yperea
 */
@Entity(name = "DiagnosticCause")
@Table(name = "DIAGNOSTIC_CAUSES")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class DiagnosticCause {

    @Id
    @Column(name = "DiagnosticCauseID")
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

    @OneToMany(mappedBy = "cause", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Diagnostic> diagnostics = new HashSet<>();

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
