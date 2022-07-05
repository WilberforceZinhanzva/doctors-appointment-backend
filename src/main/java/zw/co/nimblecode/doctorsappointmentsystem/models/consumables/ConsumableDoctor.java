package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

import java.util.List;

@Data
public class ConsumableDoctor implements Consumable {
    private String username;
    private String password;
    private String fullname;
    private List<String> fieldsOfSpecialization;

    @Override
    public Validity checkValidity() {
        return null;
    }
}
