package co.net.quiron.domain.person;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PersonType")
@Table(name = "PERSON_TYPES")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class PersonType {

    @Id
    @Column(name = "PersonTypeID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "PersonTypeName")
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "personType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Person> persons = new HashSet<>();

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
