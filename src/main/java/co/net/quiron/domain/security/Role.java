package co.net.quiron.domain.security;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Role")
@Table(name = "ROLES")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @Column(name = "RoleID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "RoleName")
    private String name;

    @NonNull
    @Column(name = "Description")
    private String description;

/*    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();*/

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
