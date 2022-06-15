package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableRole;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name="roles")
public class Role implements Serializable{
    @Id
    private String id = UUID.randomUUID().toString();
    private String role;
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
    joinColumns = @JoinColumn(name="role",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="permission", referencedColumnName = "id"))
    private List<Permission> permissions = new ArrayList<>();
    @ManyToMany(mappedBy = "roles")
    private Set<Credentials> credentials = new HashSet<>();

    @Override
    public TransferableRole serializeForTransfer() {
        return new TransferableRole(this);
    }
}
