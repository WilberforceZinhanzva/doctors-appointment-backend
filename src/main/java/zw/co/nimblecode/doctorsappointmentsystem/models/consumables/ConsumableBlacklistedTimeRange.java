package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

import java.time.LocalTime;

@Data
public class ConsumableBlacklistedTimeRange implements Consumable {
    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public Validity checkValidity() {
        return null;
    }
}
