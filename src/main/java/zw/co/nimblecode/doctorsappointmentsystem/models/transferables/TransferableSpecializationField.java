package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Doctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.SpecializationField;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TransferableSpecializationField implements Transferable{
    private String id;
    private String field;
    private Set<TransferableAppointmentType> appointmentTypes = new HashSet<>();
    private Set<TransferableDoctor> doctors = new HashSet<>();

    public TransferableSpecializationField(SpecializationField specializationField){
        this.id = specializationField.getId();
        this.field = specializationField.getField();
        this.appointmentTypes = specializationField.getAppointmentTypes().stream().map(AppointmentType::serializeForTransfer).collect(Collectors.toSet());
        this.doctors = specializationField.getDoctors().stream().map(Doctor::serializeForTransfer).collect(Collectors.toSet());

    }
}
