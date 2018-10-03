package co.net.quiron.domain.admin;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * This class represents the State or Province domain for the application.
 *
 * @autor yperea
 */
@Entity(name = "States")
@Table(name = "STATE_PROVINCES")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class State {

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

    @NonNull
    @ManyToOne
    private Country country;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate; //@CreationTimestamp annotation only works with Date or Calendar types

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}

