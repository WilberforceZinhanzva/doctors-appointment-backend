package zw.co.nimblecode.doctorsappointmentsystem.services;

import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceAlreadyExistsException;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceNotFoundException;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableSpecializedField;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.SpecializationField;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableSpecializationField;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.AppointmentTypeRepository;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.SpecializationFieldRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecializationFieldsService {

    private SpecializationFieldRepository specializationFieldRepository;
    private AppointmentTypeRepository appointmentTypeRepository;

    public SpecializationFieldsService(SpecializationFieldRepository specializationFieldRepository, AppointmentTypeRepository appointmentTypeRepository) {
        this.specializationFieldRepository = specializationFieldRepository;
        this.appointmentTypeRepository = appointmentTypeRepository;
    }

    public TransferableSpecializationField createSpecializationField(ConsumableSpecializedField consumableSpecializedField) {
        if (specializationFieldRepository.existsByFieldIgnoreCase(consumableSpecializedField.getField()))
            throw new ResourceAlreadyExistsException("Specialization field already exists!");

        List<AppointmentType> appointmentTypes = appointmentTypeRepository.findAllById(consumableSpecializedField.getAppointmentTypes());

        SpecializationField specializationField = new SpecializationField();
        specializationField.setField(consumableSpecializedField.getField());
        specializationField.setAppointmentTypes(new ArrayList<>(appointmentTypes));

        return specializationFieldRepository.save(specializationField).serializeForTransfer();

    }

    public List<TransferableSpecializationField> specializationFields() {
        return specializationFieldRepository.findAll().stream().map(SpecializationField::serializeForTransfer).collect(Collectors.toList());
    }

    public TransferableSpecializationField deleteSpecializationField(String id) {
        Optional<SpecializationField> specializationField = specializationFieldRepository.findById(id);
        if (specializationField.isEmpty())
            throw new ResourceNotFoundException("Specialization field not found!");
        specializationFieldRepository.delete(specializationField.get());
        return specializationField.get().serializeForTransfer();
    }
}
