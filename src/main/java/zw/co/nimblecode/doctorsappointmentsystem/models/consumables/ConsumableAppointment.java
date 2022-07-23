package zw.co.nimblecode.doctorsappointmentsystem.models.consumables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.PaymentType;
import zw.co.nimblecode.doctorsappointmentsystem.utils.Validity;

@Data
public class ConsumableAppointment implements Consumable {
    private String appointmentDateAndTime;
    private String appointmentType;
    private String doctorId;
    private PaymentType paymentType;

    @Override
    public Validity checkValidity() {
        return null;
    }
}
