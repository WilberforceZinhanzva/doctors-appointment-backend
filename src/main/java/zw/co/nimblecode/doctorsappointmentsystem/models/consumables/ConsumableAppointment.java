package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

@Data
public class ConsumableAppointment implements Consumable {
    private String appointmentDateAndTime;
    private String appointmentTypeId;
    private String doctorId;

    @Override
    public Validity checkValidity() {
        return null;
    }
}
