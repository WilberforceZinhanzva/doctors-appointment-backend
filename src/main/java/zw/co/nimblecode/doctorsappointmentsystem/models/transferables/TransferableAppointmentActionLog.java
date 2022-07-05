package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentActionLog;

@Data
public class TransferableAppointmentActionLog implements Transferable {
    private String id;
    private String action;
    private String reason;
    private String appointmentId;

    public TransferableAppointmentActionLog(AppointmentActionLog appointmentActionLog) {
        this.id = appointmentActionLog.getId();
        this.action = appointmentActionLog.getAction();
        this.reason = appointmentActionLog.getReason();
        this.appointmentId = appointmentActionLog.getAppointment().getId();
    }
}
