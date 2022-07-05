package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Assistant;

@Data
public class TransferableAssistant implements Transferable {
    private String id;
    private String fullname;

    public TransferableAssistant(Assistant assistant) {
        this.id = assistant.getId();
        this.fullname = assistant.getFullname();
    }
}
