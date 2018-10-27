package co.net.quiron.domain.person;


import co.net.quiron.domain.admin.AddressType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "EntityAddress")
@Table(name = "ENTITY_ADDRESSES")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class EntityAddress implements Serializable {

    @Id
    @ManyToOne
    @NonNull
    @JoinColumn(name = "EntityID")
    private BusinessEntity entity;

    @Id
    @ManyToOne
    @NonNull
    @JoinColumn(name = "AddressID")
    private Address address;

    @Id
    @ManyToOne
    @NonNull
    @JoinColumn(name = "AddressTypeID")
    private AddressType addressType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;
}
