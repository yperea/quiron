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
 * This class represents Diagnostic domain for the application.
 *
 * @autor yperea
 */
@Entity(name = "Diagnostic")
@Table(name = "DIAGNOSTICS")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Diagnostic {

    @Id
    @Column(name = "DiagnosticID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;


    @Column(name = "Comments")
    private String comments;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "VisitID")
    private Visit visit;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "DiagnosticCauseID")
    private DiagnosticCause cause;


    /*TODO: Solve error when is FetchType.LAZY. org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... could not initialize proxy - no Session*/
    @OneToMany(mappedBy = "diagnostic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Prescription> prescriptions = new HashSet<>();

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
