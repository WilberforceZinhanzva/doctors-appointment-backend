package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableAppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.services.AppointmentTypeService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/appointment-types")
public class AppointmentTypeController {

    private AppointmentTypeService appointmentTypeService;

    public AppointmentTypeController(AppointmentTypeService appointmentTypeService) {
        this.appointmentTypeService = appointmentTypeService;
    }

    @PostMapping
    public ResponseEntity<TransferableAppointmentType> createAppointment(@RequestBody ConsumableAppointmentType consumableAppointmentType) {
        return ResponseEntity.ok(appointmentTypeService.createAppointmentType(consumableAppointmentType));
    }

    @GetMapping
    public ResponseEntity<Set<TransferableAppointmentType>> appointmentTypes() {
        return ResponseEntity.ok(appointmentTypeService.appointmentTypes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransferableAppointmentType> deleteAppointmentType(@PathVariable("id") String id) {
        return ResponseEntity.ok(appointmentTypeService.deleteAppointmentType(id));
    }
}
