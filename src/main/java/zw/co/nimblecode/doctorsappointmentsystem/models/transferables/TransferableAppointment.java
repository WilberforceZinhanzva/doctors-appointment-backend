package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Appointment;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentStatus;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.PaymentType;

@Data
public class TransferableAppointment implements Transferable {
    private String id;
    private TransferableAppointmentType appointmentType;
    private TransferableAppointmentTime appointmentTime;
   // private Set<TransferableAppointmentActionLog> appointmentActionLog = new HashSet<>();
    private AppointmentStatus appointmentStatus;
    private TransferableDoctor doctor;
    private TransferablePatient patient;
    private PaymentType paymentType;

    public TransferableAppointment(Appointment appointment) {
        this.id = appointment.getId();
        this.appointmentType = appointment.getAppointmentType().serializeForTransfer();
        this.appointmentTime = appointment.getAppointmentTime().serializeForTransfer();
        //this.appointmentActionLog = appointment.getAppointmentActionLog().stream().map(AppointmentActionLog::serializeForTransfer).collect(Collectors.toSet());
        this.appointmentStatus = appointment.getAppointmentStatus();
        this.doctor = appointment.getDoctor().serializeForTransfer();
        this.patient = appointment.getPatient().serializeForTransfer();
        this.paymentType = appointment.getPaymentType();
    }
}
