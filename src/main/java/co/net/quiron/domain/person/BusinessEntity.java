package co.net.quiron.domain.person;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * This class represents a Business entity.
 */
@Entity(name="BusinessEntity")
@Table(name="ENTITIES")
@Data
@NoArgsConstructor
public class BusinessEntity {

    @Id
    @Column(name = "EntityID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate; //@CreationTimestamp annotation only works with Date or Calendar types

    /*
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "ENTITY_ADDRESSES",
               joinColumns = {@JoinColumn(name = "EntityID")},
               inverseJoinColumns = @JoinColumn(name = "AddressID"))
    @MapKeyJoinColumn(name = "AddressTypeID")
    @ElementCollection
    //private Set<Address> addresses = new HashSet<>();
    private Map<AddressType, Address> addresses = new HashMap<>();
    */
    /*
    @OneToMany(mappedBy = "entityAddressId.entity", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<EntityAddress> entityAddresses = new HashSet<>();
    */

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntityAddress> entityAddresses = new HashSet<>();

}
