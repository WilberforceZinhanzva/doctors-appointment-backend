package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import lombok.ToString;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.SpecializationField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TransferableSpecializationField implements Transferable {
    private String id;
    private String field;
    @ToString.Exclude
    private List<TransferableAppointmentType> appointmentTypes = new ArrayList<>();

    public TransferableSpecializationField(SpecializationField specializationField) {
        this.id = specializationField.getId();
        this.field = specializationField.getField();
        this.appointmentTypes = specializationField.getAppointmentTypes().stream().map(AppointmentType::serializeForTransfer).collect(Collectors.toList());


    }
}
