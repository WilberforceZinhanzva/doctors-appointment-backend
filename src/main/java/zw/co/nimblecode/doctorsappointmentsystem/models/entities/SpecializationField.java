package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableSpecializationField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="specialization_fields")
public class SpecializationField implements Serializable{
    @Id
    private String id = UUID.randomUUID().toString();
    private String field;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "specialization_field_appointment_types",
            joinColumns = @JoinColumn(name="specialization_field",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="appointment_type",referencedColumnName = "id"))
    private Set<AppointmentType> appointmentTypes = new HashSet<>();
    @ManyToMany(mappedBy = "fieldOfSpecialization", cascade = {CascadeType.DETACH})
    private Set<Doctor> doctors = new HashSet<>();

    @Override
    public TransferableSpecializationField serializeForTransfer() {
        return new TransferableSpecializationField(this);
    }
}
