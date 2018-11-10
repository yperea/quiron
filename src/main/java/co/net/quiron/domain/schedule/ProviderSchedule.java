package co.net.quiron.domain.schedule;

import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.domain.schedule.ShiftSchedule;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "ProviderSchedule")
@Table(name = "PROVIDERS_SCHEDULES")
public class ProviderSchedule implements Serializable {

/*
    @EmbeddedId
    private ProviderScheduleKey id;
*/

    @Id
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "ProviderID")
    private Provider provider;

    @Id
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="ShiftID", referencedColumnName = "ShiftID"),
            @JoinColumn(name="WeekDayID", referencedColumnName = "WeekDayID"),
            })
    private ShiftSchedule shiftSchedule;

    @Id
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "LocationID")
    private Address address;

    @Id
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "OrganizationID")
    private Organization organization;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

}
