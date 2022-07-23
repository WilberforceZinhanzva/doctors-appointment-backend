package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentStatus;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.PaymentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_time")
    private AppointmentTime appointmentTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
    private List<AppointmentActionLog> appointmentActionLog = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "appointment_type")
    private AppointmentType appointmentType;
    private AppointmentStatus appointmentStatus;
    @ManyToOne
    @JoinColumn(name = "doctor")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Override
    public TransferableAppointment serializeForTransfer() {
        return new TransferableAppointment(this);
    }
}
