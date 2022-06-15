package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.SpecializationField;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TransferableAppointmentType implements Transferable{
    private String id;
    private String name;
    private String description;
    private int duration;
    private String image;
    private Set<TransferableSpecializationField> specializationFields = new HashSet<>();

    public TransferableAppointmentType(AppointmentType appointmentType){
        this.id = appointmentType.getId();
        this.name = appointmentType.getName();
        this.description = appointmentType.getDescription();
        this.duration = appointmentType.getDuration();
        this.image = appointmentType.getImage();
        this.specializationFields = appointmentType.getSpecializationFields().stream().map(SpecializationField::serializeForTransfer).collect(Collectors.toSet());

    }

}
