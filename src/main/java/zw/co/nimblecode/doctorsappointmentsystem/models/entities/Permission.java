package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferablePermission;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "permissions")
public class Permission implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private String permission;
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>();

    @Override
    public TransferablePermission serializeForTransfer() {
        return new TransferablePermission(this);
    }
}
