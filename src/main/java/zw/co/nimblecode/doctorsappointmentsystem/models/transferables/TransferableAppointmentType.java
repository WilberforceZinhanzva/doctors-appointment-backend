package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import lombok.Data;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentType;

@Data
public class TransferableAppointmentType implements Transferable {
    private String id;
    private String name;
    private String description;
    private int duration;
    private String image;


    public TransferableAppointmentType(AppointmentType appointmentType) {
        this.id = appointmentType.getId();
        this.name = appointmentType.getName();
        this.description = appointmentType.getDescription();
        this.duration = appointmentType.getDuration();
        this.image = appointmentType.getImage();

    }

}
