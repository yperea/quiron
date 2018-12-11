package co.net.quiron.domain.location;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the States or Province domain for the application.
 *
 * @autor yperea
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "State")
@Table(name = "STATE_PROVINCES")
public class State implements Serializable {

    //@Transient
    //private final Logger logger = LogManager.getLogger(this.getClass());

    @Id
    @Column(name = "StateProvinceID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "StateProvinceCode")
    private String code;

    @NonNull
    @Column(name = "Name")
    private String name;

    @JsonBackReference
    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "CountryID")
    private Country country;

    /*TODO: Solve error when is FetchType.LAZY. org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... could not initialize proxy - no Session*/
    @JsonManagedReference
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Address> addresses = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    /**
     * Add address.
     *
     * @param address the address
     */
    public void addAddress(Address address) {
        addresses.add(address);
        address.setState(this);
    }

    /**
     * Remove address.
     *
     * @param address the address
     */
    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setState(null);
    }
}

