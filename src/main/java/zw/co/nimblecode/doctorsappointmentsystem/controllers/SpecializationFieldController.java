package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableSpecializedField;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableSpecializationField;
import zw.co.nimblecode.doctorsappointmentsystem.services.SpecializationFieldsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specialization-fields")
public class SpecializationFieldController {

    private SpecializationFieldsService specializationFieldsService;

    public SpecializationFieldController(SpecializationFieldsService specializationFieldsService) {
        this.specializationFieldsService = specializationFieldsService;
    }

    @PostMapping
    public ResponseEntity<TransferableSpecializationField> createSpecializationField(@RequestBody ConsumableSpecializedField consumableSpecializedField) {
        return ResponseEntity.ok(specializationFieldsService.createSpecializationField(consumableSpecializedField));
    }

    @GetMapping
    public ResponseEntity<List<TransferableSpecializationField>> specializationFields() {
        return ResponseEntity.ok(specializationFieldsService.specializationFields());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransferableSpecializationField> deleteSpecializationField(@PathVariable("id") String id) {
        return ResponseEntity.ok(specializationFieldsService.deleteSpecializationField(id));
    }
}
