package zw.co.nimblecode.doctorsappointmentsystem.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.Consumable;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumablePermission;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableRole;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Permission;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Role;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.PermissionRepository;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.RoleRepository;
import zw.co.nimblecode.doctorsappointmentsystem.services.RolesAndPermissionsService;

import java.util.Optional;

@Service
@Slf4j
public class StartupService {

    private RolesAndPermissionsService rolesAndPermissionsService;
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;


    public StartupService(RolesAndPermissionsService rolesAndPermissionsService, PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.rolesAndPermissionsService = rolesAndPermissionsService;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

    public void init(){
        log.info("[Initializing]");
        createPermissions();
        createRoles();
        log.info("[Initialization Complete]");
    }

    public void createPermissions(){
        ConsumablePermission createAnAppointment = new ConsumablePermission();
        createAnAppointment.setPermission("CREATE_APPOINTMENT");

        ConsumablePermission attendToPatients = new ConsumablePermission();
        attendToPatients.setPermission("ATTEND_PATIENTS");

        if(!permissionRepository.existsByPermissionIgnoreCase(createAnAppointment.getPermission()))
            rolesAndPermissionsService.createPermission(createAnAppointment); log.info("CREATE PERMISSION: [create_an_appointment]    done");
        if(!permissionRepository.existsByPermissionIgnoreCase(attendToPatients.getPermission()))
            rolesAndPermissionsService.createPermission(attendToPatients); log.info("CREATE PERMISSION: [attend_to_patients]    done");


    }

    public void createRoles(){
        if(!roleRepository.existsByRoleIgnoreCase("DOCTOR")){
            Optional<Permission> attendPatientsPermission = permissionRepository.findByPermission("ATTEND_PATIENTS");
            ConsumableRole consumableRole = new ConsumableRole();
            consumableRole.setRole("DOCTOR");
            if(attendPatientsPermission.isPresent()){
                consumableRole.getPermissions().add(attendPatientsPermission.get().getPermission());
            }
            rolesAndPermissionsService.createRole(consumableRole);
            log.info("CREATE ROLE: [DOCTOR]    done");
        }

        if(!roleRepository.existsByRoleIgnoreCase("PATIENT")){
            Optional<Permission> createAppointmentPermission = permissionRepository.findByPermission("CREATE_APPOINTMENT");
            ConsumableRole consumableRole = new ConsumableRole();
            consumableRole.setRole("PATIENT");
            if(createAppointmentPermission.isPresent()){
                consumableRole.getPermissions().add(createAppointmentPermission.get().getPermission());
            }
            rolesAndPermissionsService.createRole(consumableRole);
            log.info("CREATE ROLE: [PATIENT]    done");
        }
    }
}
