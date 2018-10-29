package co.net.quiron.domain.location;

import co.net.quiron.domain.location.AddressType;
import co.net.quiron.domain.location.State;
import co.net.quiron.domain.person.BusinessEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Address")
@Table(name = "ADDRESSES")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Address {

    @Id
    @Column(name = "AddressID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "AddressLine1")
    private String addressLine1;

    @Column(name = "AddressLine2")
    private String addressLine2;

    @NonNull
    @Column(name = "City")
    private String city;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "StateProvinceID")
    private State state;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "AddressTypeID")
    private AddressType addressType;


    @NonNull
    @Column(name = "PostalCode")
    private String postalCode;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private Set<BusinessEntity> entities = new HashSet<>();

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
