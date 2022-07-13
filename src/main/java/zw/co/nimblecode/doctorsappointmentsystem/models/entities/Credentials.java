package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableCredentials;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "credentials")
public class Credentials implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private String username;
    private String password;
    @OneToOne(mappedBy = "credentials")
    private User user;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "credential_roles",
            joinColumns = @JoinColumn(name = "credential", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public TransferableCredentials serializeForTransfer() {
        return new TransferableCredentials(this);
    }
}
