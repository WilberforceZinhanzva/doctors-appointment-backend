package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.BlacklistedTimeRange;

import java.time.LocalTime;

@Data
public class TransferableBlacklistedTimeRange implements Transferable {
    private String id;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean active;

    public TransferableBlacklistedTimeRange(BlacklistedTimeRange blacklistedTimeRange) {
        this.id = blacklistedTimeRange.getId();
        this.startTime = blacklistedTimeRange.getStartTime();
        this.endTime = blacklistedTimeRange.getEndTime();
        this.active = blacklistedTimeRange.isActive();

    }
}
