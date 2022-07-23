package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.TimeSlot;
import zw.co.nimblecode.doctorsappointmentsystem.services.TimeSlotsService;
import zw.co.nimblecode.doctorsappointmentsystem.utils.GlobalUtilities;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:30000"})
@RestController
@RequestMapping("/api/v1/time-slots")
public class TimeSlotsController {

    private TimeSlotsService timeSlotsService;

    public TimeSlotsController(TimeSlotsService timeSlotsService) {
        this.timeSlotsService = timeSlotsService;
    }

    @GetMapping("/{doctorId}/{date}/{appointmentType}")
    public ResponseEntity<List<TimeSlot>> timeSlots(@PathVariable("doctorId") String doctorId, @PathVariable("date") String date, @PathVariable("appointmentType") String appointmentTypeId) {
        return ResponseEntity.ok(timeSlotsService.timeSlots(doctorId, GlobalUtilities.parseDate(date), appointmentTypeId));
    }
}
