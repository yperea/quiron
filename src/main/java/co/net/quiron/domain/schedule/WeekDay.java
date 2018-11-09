package co.net.quiron.domain.schedule;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "WeekDay")
@Table(name = "WEEK_DAYS")
public class WeekDay {

    @Id
    @NonNull
    @Column(name = "WeekDayCode")
    private String code;

    @OneToMany(mappedBy = "weekDay",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.EAGER)
    private Set<ShiftSchedule> shiftSchedules = new HashSet<>();

    @NonNull
    @Column(name = "Name")
    private String name;

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}
