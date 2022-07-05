package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Credentials;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TransferableCredentials implements Transferable {
    private String id;
    private String username;
    private String password;
    private Set<TransferableRole> roles = new HashSet<>();

    public TransferableCredentials(Credentials credentials) {
        this.id = credentials.getId();
        this.username = credentials.getUsername();
        this.password = credentials.getPassword();
        this.roles = credentials.getRoles().stream().map(Role::serializeForTransfer).collect(Collectors.toSet());
    }

}
