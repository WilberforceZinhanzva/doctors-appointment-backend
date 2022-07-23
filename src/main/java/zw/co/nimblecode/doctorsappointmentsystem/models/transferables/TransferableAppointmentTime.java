package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentTime;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentTimeStatus;
import zw.co.nimblecode.doctorsappointmentsystem.utils.GlobalUtilities;

@Data
public class TransferableAppointmentTime implements Transferable {
    private String id;
    private String date;
    private Integer duration;
    private String appointmentId;
    private AppointmentTimeStatus appointmentTimeStatus;

    public TransferableAppointmentTime(AppointmentTime appointmentTime) {
        this.id = appointmentTime.getId();
        this.date = appointmentTime.getDate().format(GlobalUtilities.dateTimeFormatter());
        this.duration = appointmentTime.getDuration();
        this.appointmentId = appointmentTime.getAppointment().getId();
        this.appointmentTimeStatus = appointmentTime.getAppointmentTimeStatus();
    }
}
