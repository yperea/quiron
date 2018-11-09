package co.net.quiron.domain.schedule;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "ShiftSchedule")
@Table(name = "SHIFTS_SCHEDULES")
public class ShiftSchedule {

    @Id
    @NonNull
    @ManyToOne
    @JoinColumn(name = "ShiftID")
    private Shift shift;

    @Id
    @NonNull
    @ManyToOne
    @JoinColumn(name = "WeekDayCode")
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
