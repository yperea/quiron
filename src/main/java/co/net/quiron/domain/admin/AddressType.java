package co.net.quiron.domain.admin;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;


}

