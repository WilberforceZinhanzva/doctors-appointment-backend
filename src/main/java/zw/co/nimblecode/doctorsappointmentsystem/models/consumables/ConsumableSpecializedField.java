package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConsumableSpecializedField implements Consumable {
    private String field;
    private List<String> appointmentTypes = new ArrayList<>();

    @Override
    public Validity checkValidity() {
        return null;
    }
}
