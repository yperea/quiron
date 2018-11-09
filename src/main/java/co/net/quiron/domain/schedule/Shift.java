package co.net.quiron.domain.schedule;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Shift")
@Table(name = "SHIFTS")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Shift {


    @Id
    @Column(name = "ShiftID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ShiftSchedule> shiftSchedules = new HashSet<>();

    @NonNull
    @Column(name = "Name")
    private String name;

    @NonNull
    @Column(name = "Description")
    private String description;

    @NonNull
    @Column(name = "StartDate")
    private LocalDate startDate;

    @NonNull
    @Column(name = "EndDate")
    private LocalDate endDate;

    @NonNull
    @Column(name = "Status")
    private String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate;

/*
    @CreationTimestamp
    @Convert(converter = TimestampAttributeConverter.class)
    private LocalDateTime createDate;
*/

}
