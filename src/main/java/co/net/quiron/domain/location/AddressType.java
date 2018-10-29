package co.net.quiron.domain.location;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * This class represents the Address type domain for the application.
 */
@Entity(name = "AddressType")
@Table(name = "ADDRESS_TYPES")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AddressType {

    @Id
    @Column(name = "AddressTypeID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "Name")
    private String name;

    /*
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "addresstypes")
    private Set<BusinessEntity> entities = new HashSet<>();
    */

/*
    @OneToMany(mappedBy = "entityAddressId.addressType", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<EntityAddress> entityAddresses = new HashSet<>();
*/

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "addressType", cascade = CascadeType.ALL, orphanRemoval = true)
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
}
