package zw.co.nimblecode.doctorsappointmentsystem.services;

import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceAlreadyExistsException;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceNotFoundException;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumablePermission;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableRole;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Permission;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Role;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferablePermission;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableRole;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.PermissionRepository;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolesAndPermissionsService {

    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;

    public RolesAndPermissionsService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }


    public TransferablePermission createPermission(ConsumablePermission consumablePermission) {
        if (permissionRepository.existsByPermissionIgnoreCase(consumablePermission.getPermission()))
            throw new ResourceAlreadyExistsException("Permission already exists!");
        Permission permission = new Permission();
        permission.setPermission(consumablePermission.getPermission());
        return permissionRepository.save(permission).serializeForTransfer();
    }

    public List<TransferablePermission> permissions() {
        return permissionRepository.findAll().stream().map(Permission::serializeForTransfer).collect(Collectors.toList());
    }

    public TransferablePermission deletePermission(String id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        if (permission.isEmpty())
            throw new ResourceNotFoundException("Permission not found!");
        permissionRepository.delete(permission.get());
        return permission.get().serializeForTransfer();
    }

    public TransferableRole createRole(ConsumableRole consumableRole) {
        if (roleRepository.existsByRoleIgnoreCase(consumableRole.getRole()))
            throw new ResourceAlreadyExistsException("Role already exists!");

        List<Permission> permissions = permissionRepository.findAllByPermissionInIgnoreCase(consumableRole.getPermissions());
        Role role = new Role();
        role.setRole(consumableRole.getRole());
        role.setPermissions(permissions);
        return roleRepository.save(role).serializeForTransfer();

    }

    public List<TransferableRole> roles() {
        return roleRepository.findAll().stream().map(Role::serializeForTransfer).collect(Collectors.toList());
    }

    public TransferableRole deleteRole(String id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty())
            throw new ResourceNotFoundException("Role not found!");
        roleRepository.delete(role.get());
        return role.get().serializeForTransfer();
    }
}

