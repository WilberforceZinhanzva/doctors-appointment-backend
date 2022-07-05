package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

import java.util.HashSet;
import java.util.Set;

@Data
public class ConsumableRole implements Consumable {
    private String role;
    private Set<String> permissions = new HashSet<>();

    @Override
    public Validity checkValidity() {
        return new Validity(true, "Valid");
    }
}
