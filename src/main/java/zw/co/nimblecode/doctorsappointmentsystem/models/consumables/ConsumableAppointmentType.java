package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;


import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

@Data
public class ConsumableAppointmentType implements Consumable {
    private String name;
    private String description;
    private int duration;
    private String image;

    @Override
    public Validity checkValidity() {
        return null;
    }
}
