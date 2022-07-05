package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private String role;
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission", referencedColumnName = "id"))
    private List<Permission> permissions = new ArrayList<>();
    @ManyToMany(mappedBy = "roles")
    private List<Credentials> credentials = new ArrayList<>();

    @Override
    public TransferableRole serializeForTransfer() {
        return new TransferableRole(this);
    }
}
