package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Assistant;

public class TransferableAssistant implements Transferable{
    private String id;
    private String fullname;

    public TransferableAssistant(Assistant assistant){
        this.id = assistant.getId();
        this.fullname = assistant.getFullname();
    }
}
