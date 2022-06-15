package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Doctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.SpecializationField;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TransferableDoctor implements Transferable{
    private String id;
    private String fullname;
    private boolean active;
    private Set<TransferableSpecializationField> specializationFields = new HashSet<>();

    public TransferableDoctor(Doctor doctor){
        this.id = doctor.getId();
        this.fullname = doctor.getFullname();
        this.active = doctor.isActive();
        this.specializationFields = doctor.getFieldOfSpecialization().stream().map(SpecializationField::serializeForTransfer).collect(Collectors.toSet());
    }
}
