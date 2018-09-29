package co.net.quiron.domain.admin;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "CreatedDate")
    private LocalDate createdDate;

    @Column(name = "ModifiedDate")
    private LocalDate modifiedDate;

}

