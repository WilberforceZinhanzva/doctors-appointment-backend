package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferablePatient;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Patient extends User {
    private String fullname;
    private String address;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new HashSet<>();
    @Override
    public TransferablePatient serializeForTransfer() {
        return new TransferablePatient(this);
    }
}
