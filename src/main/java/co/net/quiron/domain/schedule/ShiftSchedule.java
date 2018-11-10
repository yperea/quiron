package co.net.quiron.domain.schedule;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "ShiftSchedule")
@Table(name = "SHIFTS_SCHEDULES")
public class ShiftSchedule implements Serializable {

    @Id
    @NonNull
    //@ToString.Exclude
    //@EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "ShiftID")
    private Shift shift;

    @Id
    @NonNull
    //@ToString.Exclude
    //@EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "WeekDayID")
    private WeekDay weekDay;

    @NonNull
    @Column(name = "StartTime")
    private LocalDateTime startTime;

    @NonNull
    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

}
