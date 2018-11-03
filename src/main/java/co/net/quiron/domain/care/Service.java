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
 * This class represents the Service domain for the application.
 *
 * @autor yperea
 */
@Entity(name = "Service")
@Table(name = "SERVICES")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Service {

    @Id
    @Column(name = "ServiceID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Visit> visits = new HashSet<>();

    /* Using Database Default value with a LocalDate type for createdDate
    @CreationTimestamp
    @Column(name = "CreatedDate", insertable = false)
    private LocalDate createdDate;
    */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate; //@CreationTimestamp annotation only works with Date or Calendar types

    /*
    @Column(name = "ModifiedDate")
    private LocalDate modifiedDate;
    */
    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}
