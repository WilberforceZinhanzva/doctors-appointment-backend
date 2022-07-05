package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumablePermission;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableRole;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferablePermission;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableRole;
import zw.co.nimblecode.doctorsappointmentsystem.services.RolesAndPermissionsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authorities")
public class RolesAndPermissionsController {
    private RolesAndPermissionsService rolesAndPermissionsService;

    public RolesAndPermissionsController(RolesAndPermissionsService rolesAndPermissionsService) {
        this.rolesAndPermissionsService = rolesAndPermissionsService;
    }

    @PostMapping("/permissions")
    public ResponseEntity<TransferablePermission> createPermission(@RequestBody ConsumablePermission consumablePermission) {
        return ResponseEntity.ok(rolesAndPermissionsService.createPermission(consumablePermission));
    }

    @GetMapping("/permissions")
    public ResponseEntity<List<TransferablePermission>> permissions() {
        return ResponseEntity.ok(rolesAndPermissionsService.permissions());
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<TransferablePermission> deletePermission(@PathVariable("id") String id) {
        return ResponseEntity.ok(rolesAndPermissionsService.deletePermission(id));
    }

    @PostMapping("/roles")
    public ResponseEntity<TransferableRole> createRole(@RequestBody ConsumableRole consumableRole) {
        return ResponseEntity.ok(rolesAndPermissionsService.createRole(consumableRole));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<TransferableRole>> roles() {
        return ResponseEntity.ok(rolesAndPermissionsService.roles());
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<TransferableRole> deleteRole(@PathVariable("id") String id) {
        return ResponseEntity.ok(rolesAndPermissionsService.deleteRole(id));
    }

}
