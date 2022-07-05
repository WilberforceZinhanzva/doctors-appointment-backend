package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableAssistant;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableDoctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumablePatient;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAssistant;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableDoctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferablePatient;
import zw.co.nimblecode.doctorsappointmentsystem.services.RegistrationService;


@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/doctors")
    public ResponseEntity<TransferableDoctor> registerDoctor(@RequestBody ConsumableDoctor consumableDoctor) {
        return ResponseEntity.ok(registrationService.registerDoctor(consumableDoctor));
    }

    @PostMapping("/patients")
    public ResponseEntity<TransferablePatient> registerPatient(@RequestBody ConsumablePatient consumablePatient) {
        return ResponseEntity.ok(registrationService.registerPatient(consumablePatient));
    }

    @PostMapping("/assistants")
    public ResponseEntity<TransferableAssistant> registerAssistant(@RequestBody ConsumableAssistant consumableAssistant) {
        return ResponseEntity.ok(registrationService.registerAssistant(consumableAssistant));
    }
}
