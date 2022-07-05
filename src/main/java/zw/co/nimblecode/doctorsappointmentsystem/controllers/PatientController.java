package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferablePatient;
import zw.co.nimblecode.doctorsappointmentsystem.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<TransferablePatient>> patients() {
        return ResponseEntity.ok(patientService.patients());
    }
}
