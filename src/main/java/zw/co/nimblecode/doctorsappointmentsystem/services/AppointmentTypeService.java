package zw.co.nimblecode.doctorsappointmentsystem.services;

import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceAlreadyExistsException;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceNotFoundException;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableAppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.AppointmentTypeRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentTypeService {

    private AppointmentTypeRepository appointmentTypeRepository;

    public AppointmentTypeService(AppointmentTypeRepository appointmentTypeRepository) {
        this.appointmentTypeRepository = appointmentTypeRepository;
    }

    public TransferableAppointmentType createAppointmentType(ConsumableAppointmentType consumableAppointmentType) {
        if (appointmentTypeRepository.existsByNameIgnoreCase(consumableAppointmentType.getName()))
            throw new ResourceAlreadyExistsException("Appointment type already exists!");

        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setName(consumableAppointmentType.getName());
        appointmentType.setDescription(consumableAppointmentType.getDescription());
        appointmentType.setDuration(consumableAppointmentType.getDuration());
        appointmentType.setImage(consumableAppointmentType.getImage());

        return appointmentTypeRepository.save(appointmentType).serializeForTransfer();
    }

    public Set<TransferableAppointmentType> appointmentTypes() {
        return appointmentTypeRepository.findAll().stream().map(AppointmentType::serializeForTransfer).collect(Collectors.toSet());
    }

    public TransferableAppointmentType deleteAppointmentType(String id) {
        Optional<AppointmentType> appointmentType = appointmentTypeRepository.findById(id);
        if (appointmentType.isEmpty())
            throw new ResourceNotFoundException("Appointment type not found!");

        appointmentTypeRepository.delete(appointmentType.get());
        return appointmentType.get().serializeForTransfer();
    }
}
