package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

@Data
public class ConsumablePatient implements Consumable {
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String phone;
    private String email;

    @Override
    public Validity checkValidity() {
        return null;
    }
}
