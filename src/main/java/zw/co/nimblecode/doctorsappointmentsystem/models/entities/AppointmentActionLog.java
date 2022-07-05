package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointmentActionLog;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "appointment_action_logs")
public class AppointmentActionLog implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private String action;
    private String reason;
    @ManyToOne
    @JoinColumn(name = "appointment")
    private Appointment appointment;

    @Override
    public TransferableAppointmentActionLog serializeForTransfer() {
        return new TransferableAppointmentActionLog(this);
    }
}
