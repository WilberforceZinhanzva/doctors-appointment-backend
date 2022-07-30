package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.DoctorSearchKey;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableDoctor;
import zw.co.nimblecode.doctorsappointmentsystem.services.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping("/{key}/{searchValue}")
    public ResponseEntity<List<TransferableDoctor>> doctors(@PathVariable("key") DoctorSearchKey key, @PathVariable("searchValue") String searchValue) {
        return ResponseEntity.ok(doctorService.doctors(key, searchValue));
    }
}
