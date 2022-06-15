package zw.co.nimblecode.doctorsappointmentsystem.models.transferables;

import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Patient;

public class TransferablePatient implements Transferable{

    private String id;
    private String fullname;
    private String address;
    private String phone;
    private String email;

    public TransferablePatient(Patient patient){
        this.id = patient.getId();
        this.fullname = patient.getFullname();
        this.address = patient.getAddress();
        this.phone = patient.getPhone();
        this.email = patient.getEmail();
    }
}
