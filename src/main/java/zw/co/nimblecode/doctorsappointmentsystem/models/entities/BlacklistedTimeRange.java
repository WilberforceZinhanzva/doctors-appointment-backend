package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableBlacklistedTimeRange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "blacklisted_time_ranges")
public class BlacklistedTimeRange implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean active;

    @Override
    public TransferableBlacklistedTimeRange serializeForTransfer() {
        return new TransferableBlacklistedTimeRange(this);
    }
}
