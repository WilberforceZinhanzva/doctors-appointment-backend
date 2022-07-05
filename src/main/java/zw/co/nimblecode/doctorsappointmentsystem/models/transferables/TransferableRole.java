package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Permission;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TransferableRole implements Transferable {
    private String id;
    private String role;
    private Set<TransferablePermission> permissions = new HashSet<>();

    public TransferableRole(Role role) {
        this.id = role.getId();
        this.role = role.getRole();
        this.permissions = role.getPermissions().stream().map(Permission::serializeForTransfer).collect(Collectors.toSet());
    }

}
