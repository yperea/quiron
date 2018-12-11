package co.net.quiron.domain.schedule;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "WeekDay")
@Table(name = "WEEK_DAYS")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class WeekDay implements Serializable {

    @Id
    @Column(name = "WeekDayID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "WeekDayCode")
    private String weekDayCode;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "weekDay",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
