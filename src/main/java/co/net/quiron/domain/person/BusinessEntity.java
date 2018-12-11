package co.net.quiron.domain.person;

import co.net.quiron.domain.location.Address;
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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BusinessEntity {

    @Id
    @Column(name = "EntityID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    protected int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    protected Date createdDate; //@CreationTimestamp annotation only works with Date or Calendar types

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ENTITY_ADDRESSES",
            joinColumns = {@JoinColumn(name = "EntityID")},
            inverseJoinColumns = {@JoinColumn(name = "AddressID")})
    protected Set<Address> addresses = new HashSet<>();

    /**
     * Add an address to the collection.
     *
     * @param address the address
     */
    public void addAddress(Address address) {
        addresses.add(address);
    }

    /**
     * Remove an address from the collection.
     *
     * @param address the address
     */
    public void removeaddress(Address address) {
        addresses.remove(address);
    }
}
