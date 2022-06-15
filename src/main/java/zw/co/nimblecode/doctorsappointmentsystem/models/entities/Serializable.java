package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.Transferable;

public interface Serializable {
    Transferable serializeForTransfer();
}
