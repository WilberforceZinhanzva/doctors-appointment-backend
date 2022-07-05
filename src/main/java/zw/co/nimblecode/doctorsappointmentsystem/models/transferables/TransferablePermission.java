package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Permission;

@Data
public class TransferablePermission implements Transferable {
    private String id;
    private String permission;

    public TransferablePermission(Permission permission) {
        this.id = permission.getId();
        this.permission = permission.getPermission();
    }
}
