package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableAppointment;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.AppointmentSearchKey;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointment;
import zw.co.nimblecode.doctorsappointmentsystem.services.AppointmentService;

import java.util.Set;
@CrossOrigin(origins = {"http://localhost:30000"})
@RestController
@RequestMapping(value = "/api/v1/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<TransferableAppointment> createAppointment(@RequestBody ConsumableAppointment consumableAppointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(consumableAppointment));
    }

    @PutMapping("/{id}/cancel/{reason}")
    public ResponseEntity<TransferableAppointment> cancelAppointment(@PathVariable("id") String id, @PathVariable("reason") String reason) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(id, reason));
    }

    @GetMapping
    public ResponseEntity<Set<TransferableAppointment>> appointments(AppointmentSearchKey key, String searchValue) {
        return ResponseEntity.ok(appointmentService.appointments(key, searchValue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransferableAppointment> deleteAppointment(@PathVariable("id") String id) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
