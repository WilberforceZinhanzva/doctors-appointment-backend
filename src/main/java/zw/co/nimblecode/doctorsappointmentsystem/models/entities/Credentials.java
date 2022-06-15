package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableCredentials;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="credentials")
public class Credentials implements Serializable{
    @Id
    private String id = UUID.randomUUID().toString();
    private String username;
    private String password;
    @OneToOne(mappedBy = "credentials")
    private User user;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    @JoinTable(name="credential_roles",
    joinColumns = @JoinColumn(name="credential", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public TransferableCredentials serializeForTransfer() {
        return new TransferableCredentials(this);
    }
}
