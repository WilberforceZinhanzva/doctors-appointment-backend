package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.TimeSlot;
import zw.co.nimblecode.doctorsappointmentsystem.services.TimeSlotsService;
import zw.co.nimblecode.doctorsappointmentsystem.utils.GlobalUtilities;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/time-slots")
public class TimeSlotsController {

    private TimeSlotsService timeSlotsService;

    public TimeSlotsController(TimeSlotsService timeSlotsService) {
        this.timeSlotsService = timeSlotsService;
    }

    @GetMapping("/{doctorId}/{date}/{appointmentType}")
    public ResponseEntity<Set<TimeSlot>> timeSlots(@PathVariable("doctorId")String doctorId,@PathVariable("date") String date,@PathVariable("appointmentType") String appointmentTypeId){
        return ResponseEntity.ok(timeSlotsService.timeSlots(doctorId, GlobalUtilities.parseDate(date),appointmentTypeId));
    }
}
