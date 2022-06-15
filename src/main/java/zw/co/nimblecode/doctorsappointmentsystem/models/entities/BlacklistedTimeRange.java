package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name="blacklisted_time_ranges")
public class BlacklistedTimeRange {
    @Id
    private String id = UUID.randomUUID().toString();
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean active;
}
