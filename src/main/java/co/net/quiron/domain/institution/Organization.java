package co.net.quiron.domain.institution;

import co.net.quiron.domain.person.BusinessEntity;
import co.net.quiron.domain.person.Patient;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Organization")
@Table(name = "ORGANIZATIONS")
@PrimaryKeyJoinColumn(name = "OrganizationID")
public class Organization extends BusinessEntity implements Serializable {

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "OrganizationTypeID")
    protected OrganizationType organizationType;

    @Column(name = "Name")
    protected String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Patient> patients = new HashSet<>();


    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    protected Date modifiedDate;

}
