package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableDoctor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Doctor extends User {
    private String fullname;
    private boolean active;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "doctors_specialization_fields",
            joinColumns = @JoinColumn(name = "doctor", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_field", referencedColumnName = "id"))
    private List<SpecializationField> fieldOfSpecialization = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public TransferableDoctor serializeForTransfer() {
        return new TransferableDoctor(this);
    }
}
