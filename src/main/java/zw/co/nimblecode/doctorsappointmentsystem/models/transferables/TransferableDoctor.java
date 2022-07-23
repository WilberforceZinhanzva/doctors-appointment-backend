package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import lombok.ToString;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Doctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.SpecializationField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data

public class TransferableDoctor implements Transferable {
    private String id;
    private String fullname;
    private boolean active;
    @ToString.Exclude
    private List<TransferableSpecializationField> specializationFields = new ArrayList<>();

    public TransferableDoctor(Doctor doctor) {
        this.id = doctor.getId();
        this.fullname = doctor.getFullname();
        this.active = doctor.isActive();
        this.specializationFields = doctor.getFieldOfSpecialization().stream().map(SpecializationField::serializeForTransfer).collect(Collectors.toList());
    }
}
