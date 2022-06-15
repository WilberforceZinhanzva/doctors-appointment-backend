package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableDoctor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Doctor extends User{
    private String fullname;
    private boolean active;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "doctors_specialization_fields",
    joinColumns = @JoinColumn(name="doctor", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="specialization_field",referencedColumnName = "id"))
    private Set<SpecializationField> fieldOfSpecialization = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor",fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @Override
    public TransferableDoctor serializeForTransfer() {
        return new TransferableDoctor(this);
    }
}
