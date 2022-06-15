package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointmentType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="appointment_types")
public class AppointmentType implements Serializable{
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private int duration;
    private String image;
    @ManyToMany(mappedBy = "appointmentTypes", cascade = {CascadeType.DETACH})
    private Set<SpecializationField> specializationFields = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointmentType", fetch=FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @Override
    public TransferableAppointmentType serializeForTransfer() {
        return new TransferableAppointmentType(this);
    }
}
