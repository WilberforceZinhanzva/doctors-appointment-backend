package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentTimeStatus;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointmentTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "appointments_time")
public class AppointmentTime implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private LocalDateTime date;
    private Integer duration;
    @OneToOne(mappedBy = "appointmentTime")
    private Appointment appointment;
    private AppointmentTimeStatus appointmentTimeStatus;

    @Override
    public TransferableAppointmentTime serializeForTransfer() {
        return new TransferableAppointmentTime(this);
    }
}
