package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

@Data
public class ConsumableAppointmentTime implements Consumable{
    private String dateAndTime;
    private Integer durationInMinutes;
    @Override
    public Validity checkValidity() {
        return null;
    }
}
