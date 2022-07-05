package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

@Data
public class ConsumableAssistant implements Consumable {
    private String username;
    private String password;
    private String fullname;

    @Override
    public Validity checkValidity() {
        return null;
    }
}
