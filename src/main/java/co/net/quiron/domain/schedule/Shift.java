package co.net.quiron.domain.schedule;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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
