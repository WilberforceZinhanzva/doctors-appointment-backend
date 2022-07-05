package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

@Data
public class ConsumableAppointmentActionLog implements Consumable {
    @Override
    public Validity checkValidity() {
        return null;
    }
}
