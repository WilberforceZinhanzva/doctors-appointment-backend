package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

@Data
public class ConsumablePermission implements Consumable {
    private String permission;

    @Override
    public Validity checkValidity() {
        return new Validity(true, "valid");
    }
}
