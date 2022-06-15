package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentTime;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentTimeStatus;

import java.time.LocalDateTime;

@Data
public class TransferableAppointmentTime implements Transferable{
    private String id;
    private LocalDateTime date;
    private Integer duration;
    private String appointmentId;
    private AppointmentTimeStatus appointmentTimeStatus;

    public TransferableAppointmentTime(AppointmentTime appointmentTime){
        this.id = appointmentTime.getId();
        this.date = appointmentTime.getDate();
        this.duration = appointmentTime.getDuration();
        this.appointmentId = appointmentTime.getAppointment().getId();
        this.appointmentTimeStatus = appointmentTime.getAppointmentTimeStatus();
    }
}
