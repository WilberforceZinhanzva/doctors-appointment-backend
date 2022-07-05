package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableSpecializationField;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "specialization_fields")
public class SpecializationField implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private String field;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "specialization_field_appointment_types",
            joinColumns = @JoinColumn(name = "specialization_field", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_type", referencedColumnName = "id"))
    private List<AppointmentType> appointmentTypes = new ArrayList<>();
    @ManyToMany(mappedBy = "fieldOfSpecialization", cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();

    @Override
    public TransferableSpecializationField serializeForTransfer() {
        return new TransferableSpecializationField(this);
    }
}
