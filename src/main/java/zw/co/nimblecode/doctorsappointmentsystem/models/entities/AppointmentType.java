package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointmentType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "appointment_types")
public class AppointmentType implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private int duration;
    private String image;
    @ManyToMany(mappedBy = "appointmentTypes", cascade = {CascadeType.DETACH})
    private List<SpecializationField> specializationFields = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointmentType", fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public TransferableAppointmentType serializeForTransfer() {
        return new TransferableAppointmentType(this);
    }
}
