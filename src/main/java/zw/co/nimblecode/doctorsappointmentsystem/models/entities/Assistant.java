package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAssistant;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity

public class Assistant extends User {
    private String fullname;

    @Override
    public TransferableAssistant serializeForTransfer() {
        return new TransferableAssistant(this);
    }
}
