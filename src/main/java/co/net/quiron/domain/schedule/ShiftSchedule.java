package co.net.quiron.domain.schedule;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "ShiftSchedule")
@Table(name = "SHIFTS_SCHEDULES")
public class ShiftSchedule {


}
