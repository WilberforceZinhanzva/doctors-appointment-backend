package zw.co.nimblecode.doctorsappointmentsystem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceNotFoundException;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableAppointment;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentSearchKey;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentStatus;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentTimeStatus;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointment;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.*;
import zw.co.nimblecode.doctorsappointmentsystem.utils.GlobalUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private AppointmentTypeRepository appointmentTypeRepository;
    private TimeSlotsService timeSlotsService;
    private CredentialsRepository credentialsRepository;


    //[FROM LOGGED IN USER]
    private String patientId;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentTypeRepository appointmentTypeRepository, TimeSlotsService timeSlotsService, CredentialsRepository credentialsRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentTypeRepository = appointmentTypeRepository;
        this.timeSlotsService = timeSlotsService;
        this.credentialsRepository = credentialsRepository;
    }

    public TransferableAppointment createAppointment(ConsumableAppointment consumableAppointment) {

        //Logged In User
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(username);
        Optional<Credentials> credentials = credentialsRepository.findByUsername(username);
        if (credentials.isEmpty())
            throw new ResourceNotFoundException("Logged in user not found!");


        Optional<Doctor> doctor = doctorRepository.findById(consumableAppointment.getDoctorId());
        if (doctor.isEmpty())
            throw new ResourceNotFoundException("Doctor not found!");

        if (!doctor.get().isActive())
            throw new ResourceNotFoundException("Doctor " + doctor.get().getFullname() + " is currently not on duty!");

        Optional<Patient> patient = patientRepository.findById(credentials.get().getUser().getId());
        if (patient.isEmpty())
            throw new ResourceNotFoundException("Patient not found!");

        Optional<AppointmentType> appointmentType = appointmentTypeRepository.findByNameIgnoreCase(consumableAppointment.getAppointmentType());
        if (appointmentType.isEmpty())
            throw new ResourceNotFoundException("Appointment type not found!");

        Optional<TimeSlot> timeSlot = timeSlotsService.grabTimeSlotForAppointment(doctor.get(), GlobalUtilities.parseDateTime(consumableAppointment.getAppointmentDateAndTime()), appointmentType.get());
        if (timeSlot.isEmpty())
            throw new ResourceNotFoundException("Sorry, we cannot schedule you appointment! Please choose another time for your appointment!");

        AppointmentTime appointmentTime = new AppointmentTime();
        appointmentTime.setDate(GlobalUtilities.parseDateTime(consumableAppointment.getAppointmentDateAndTime()));
        appointmentTime.setDuration(appointmentType.get().getDuration());
        appointmentTime.setAppointmentTimeStatus(AppointmentTimeStatus.Taken);


        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(appointmentTime);
        appointment.setAppointmentType(appointmentType.get());
        appointment.setAppointmentStatus(AppointmentStatus.Assigned);
        appointment.setDoctor(doctor.get());
        appointment.setPatient(patient.get());
        appointment.setPaymentType(consumableAppointment.getPaymentType());


        appointmentTime.setAppointment(appointment);

        AppointmentActionLog appointmentActionLog = new AppointmentActionLog();
        appointmentActionLog.setAction("Created Appointment");
        appointmentActionLog.setReason("Patient created their appointment");

        appointment.getAppointmentActionLog().add(appointmentActionLog);

        return appointmentRepository.save(appointment).serializeForTransfer();

    }

    public TransferableAppointment cancelAppointment(String id, String reason) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty())
            throw new ResourceNotFoundException("Appointment not found!");
        appointment.get().setAppointmentStatus(AppointmentStatus.Cancelled);
        AppointmentActionLog appointmentActionLog = new AppointmentActionLog();
        appointmentActionLog.setAction("Cancel appointment");
        appointmentActionLog.setReason(reason);
        appointment.get().getAppointmentActionLog().add(appointmentActionLog);
        return appointmentRepository.save(appointment.get()).serializeForTransfer();
    }

    public Set<TransferableAppointment> appointments(AppointmentSearchKey key, String searchValue) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Optional.of(username).isEmpty())
            throw new ResourceNotFoundException("User not logged in!");

        Optional<Credentials> credentials = credentialsRepository.findByUsername(username);
        if (credentials.isEmpty())
            throw new ResourceNotFoundException("Credentials not found!");

        User user = credentials.get().getUser();

        List<Appointment> appointmentList = new ArrayList<>();
        if (user instanceof Doctor) {
            switch (key) {
                case AppointmentType:
                    appointmentList = appointmentRepository.findAllByDoctorAndAppointmentType_NameIgnoreCase((Doctor) user, searchValue);
                    break;
                default:
                    appointmentList = appointmentRepository.findAllByDoctor((Doctor) user);
                    break;
            }
        } else if (user instanceof Patient) {
            switch (key) {
                case AppointmentType:
                    appointmentList = appointmentRepository.findAllByPatientAndAppointmentType_NameIgnoreCase((Patient) user, searchValue);
                    break;
                case AppointmentStatus:
                    appointmentList = appointmentRepository.findAllByPatientAndAppointmentStatusNot((Patient)user,AppointmentStatus.valueOf(searchValue));
                    break;
                default:
                    appointmentList = appointmentRepository.findAllByPatient((Patient) user);
                    break;
            }
        } else {
            appointmentList = appointmentRepository.findAll();
        }

        return appointmentList.stream().map(Appointment::serializeForTransfer).collect(Collectors.toSet());
    }

    public TransferableAppointment deleteAppointment(String id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty())
            throw new ResourceNotFoundException("Appointment not found!");
        appointmentRepository.delete(appointment.get());
        return appointment.get().serializeForTransfer();
    }
}
