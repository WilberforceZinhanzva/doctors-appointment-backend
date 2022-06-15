package zw.co.nimblecode.doctorsappointmentsystem.models.entities;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeSlot {
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean taken;
}
