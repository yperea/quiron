package co.net.quiron.domain.admin;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * This class represents the State or Province domain for the application.
 *
 * @autor yperea
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "State")
@Table(name = "STATE_PROVINCES")
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "CountryID")
    private Country country;

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

