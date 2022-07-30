package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Appointment;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Doctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Patient;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findAllByDoctor_IdAndAppointmentTime_DateBetween(String doctorId, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findAllByDoctor(Doctor doctor);

    List<Appointment> findAllByPatient(Patient patient);

    List<Appointment> findAllByDoctorAndAppointmentType_NameIgnoreCase(Doctor doctor, String appointmentType);

    List<Appointment> findAllByPatientAndAppointmentType_NameIgnoreCase(Patient patient, String appointmentType);

    List<Appointment> findAllByPatientAndAppointmentStatusNot(Patient patient, AppointmentStatus status);
}
