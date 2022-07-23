package zw.co.nimblecode.doctorsappointmentsystem.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentType;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.BlacklistedTimeRange;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Permission;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.*;
import zw.co.nimblecode.doctorsappointmentsystem.services.AppointmentTypeService;
import zw.co.nimblecode.doctorsappointmentsystem.services.RegistrationService;
import zw.co.nimblecode.doctorsappointmentsystem.services.RolesAndPermissionsService;
import zw.co.nimblecode.doctorsappointmentsystem.services.SpecializationFieldsService;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StartupService {

    private RolesAndPermissionsService rolesAndPermissionsService;
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;
    private BlacklistedTimeRangeRepository blacklistedTimeRangeRepository;
    private RegistrationService registrationService;
    private CredentialsRepository credentialsRepository;
    private AppointmentTypeService appointmentTypeService;
    private AppointmentTypeRepository appointmentTypeRepository;
    private SpecializationFieldRepository specializationFieldRepository;
    private SpecializationFieldsService specializationFieldsService;



    public StartupService(RolesAndPermissionsService rolesAndPermissionsService, PermissionRepository permissionRepository, RoleRepository roleRepository, BlacklistedTimeRangeRepository blacklistedTimeRangeRepository, RegistrationService registrationService, CredentialsRepository credentialsRepository
            ,AppointmentTypeService appointmentTypeService,AppointmentTypeRepository appointmentTypeRepository
            ,SpecializationFieldsService specializationFieldsService, SpecializationFieldRepository specializationFieldRepository) {
        this.rolesAndPermissionsService = rolesAndPermissionsService;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.blacklistedTimeRangeRepository = blacklistedTimeRangeRepository;
        this.registrationService = registrationService;
        this.credentialsRepository = credentialsRepository;
        this.appointmentTypeService = appointmentTypeService;
        this.appointmentTypeRepository = appointmentTypeRepository;
        this.specializationFieldsService =specializationFieldsService;
        this.specializationFieldRepository = specializationFieldRepository;
    }

    public void init() {
        log.info("[Initializing]");
        createPermissions();
        createRoles();
        registerDefaultUsers();
        blacklistNonWorkingHours();
        registerAppointmentTypes();
        registerSpecializationFields();
        log.info("[Initialization Complete]");
    }

    public void createPermissions() {
        ConsumablePermission createAnAppointment = new ConsumablePermission();
        createAnAppointment.setPermission("CREATE_APPOINTMENT");

        ConsumablePermission attendToPatients = new ConsumablePermission();
        attendToPatients.setPermission("ATTEND_PATIENTS");

        if (!permissionRepository.existsByPermissionIgnoreCase(createAnAppointment.getPermission())) {
            rolesAndPermissionsService.createPermission(createAnAppointment);
            log.info("CREATE PERMISSION: [create_an_appointment]    done");
        }

        if (!permissionRepository.existsByPermissionIgnoreCase(attendToPatients.getPermission())) {
            rolesAndPermissionsService.createPermission(attendToPatients);
            log.info("CREATE PERMISSION: [attend_to_patients]    done");
        }


    }

    public void createRoles() {
        if (!roleRepository.existsByRoleIgnoreCase("DOCTOR")) {
            Optional<Permission> attendPatientsPermission = permissionRepository.findByPermission("ATTEND_PATIENTS");
            ConsumableRole consumableRole = new ConsumableRole();
            consumableRole.setRole("DOCTOR");
            if (attendPatientsPermission.isPresent()) {
                consumableRole.getPermissions().add(attendPatientsPermission.get().getPermission());
            }
            rolesAndPermissionsService.createRole(consumableRole);
            log.info("CREATE ROLE: [DOCTOR]    done");
        }

        if (!roleRepository.existsByRoleIgnoreCase("PATIENT")) {
            Optional<Permission> createAppointmentPermission = permissionRepository.findByPermission("CREATE_APPOINTMENT");
            ConsumableRole consumableRole = new ConsumableRole();
            consumableRole.setRole("PATIENT");
            if (createAppointmentPermission.isPresent()) {
                consumableRole.getPermissions().add(createAppointmentPermission.get().getPermission());
            }
            rolesAndPermissionsService.createRole(consumableRole);
            log.info("CREATE ROLE: [PATIENT]    done");
        }

        if(!roleRepository.existsByRoleIgnoreCase("ASSISTANT")){
            ConsumableRole consumableRole = new ConsumableRole();
            consumableRole.setRole("ASSISTANT");
            rolesAndPermissionsService.createRole(consumableRole);
            log.info("CREATE ROLE: [ASSISTANT]     done");
        }
    }


    public void registerDefaultUsers(){
        if(!credentialsRepository.existsByUsername("ADMIN")){
            ConsumableAssistant consumableAssistant = new ConsumableAssistant();
            consumableAssistant.setFullname("ADMIN");
            consumableAssistant.setUsername("ADMIN");
            consumableAssistant.setPassword("12345678");
            registrationService.registerAssistant(consumableAssistant);

            log.info("REGISTRATION: [ADMIN ASSISTANT]     done");
        }
    }

    public void registerAppointmentTypes(){
        if(!appointmentTypeRepository.existsByNameIgnoreCase("Optics")){
            ConsumableAppointmentType consumable = new ConsumableAppointmentType();
            consumable.setName("Optics");
            consumable.setDescription("Optics");
            consumable.setDuration(120);
            consumable.setImage("");
            appointmentTypeService.createAppointmentType(consumable);
            log.info("REGISTER [APPOINTMENT TYPE Optics]     done");
        }
        if(!appointmentTypeRepository.existsByNameIgnoreCase("Consultancy")){
            ConsumableAppointmentType consumable = new ConsumableAppointmentType();
            consumable.setName("Consultancy");
            consumable.setDescription("Consultancy");
            consumable.setDuration(30);
            consumable.setImage("");
            appointmentTypeService.createAppointmentType(consumable);
            log.info("REGISTER [APPOINTMENT TYPE Consultancy]     done");
        }
        if(!appointmentTypeRepository.existsByNameIgnoreCase("Surgery")){
            ConsumableAppointmentType consumable = new ConsumableAppointmentType();
            consumable.setName("Surgery");
            consumable.setDescription("Surgery");
            consumable.setDuration(240);
            consumable.setImage("");
            appointmentTypeService.createAppointmentType(consumable);
            log.info("REGISTER [APPOINTMENT TYPE Surgery]     done");
        }
        if(!appointmentTypeRepository.existsByNameIgnoreCase("Dental")){
            ConsumableAppointmentType consumable = new ConsumableAppointmentType();
            consumable.setName("Dental");
            consumable.setDescription("Dental");
            consumable.setDuration(120);
            consumable.setImage("");
            appointmentTypeService.createAppointmentType(consumable);
            log.info("REGISTER [APPOINTMENT TYPE Dental]     done");
        }
    }


    public void registerSpecializationFields(){
        List<AppointmentType> appointmentTypes = appointmentTypeRepository.findAll();
        Optional<AppointmentType> optics = appointmentTypes.stream().filter(a -> a.getName().contentEquals("Optics")).findFirst();
        Optional<AppointmentType> dental = appointmentTypes.stream().filter(a->a.getName().contentEquals("Dental")).findFirst();
        Optional<AppointmentType> surgery = appointmentTypes.stream().filter(a -> a.getName().contentEquals("Surgery")).findFirst();
        Optional<AppointmentType> consultancy = appointmentTypes.stream().filter(a->a.getName().contentEquals("Consultancy")).findFirst();


        List<String> fields = Arrays.asList("Eye Specialist","Teeth Specialist", "Cardio Vascular Specialist");
        ConsumableSpecializedField consumableSpecializedField = new ConsumableSpecializedField();
        for(String f : fields){

            if(!specializationFieldRepository.existsByFieldIgnoreCase(f)){

                switch (f){
                    case "Eye Specialist":
                       consumableSpecializedField.setField(f);
                       consumableSpecializedField.setAppointmentTypes(Arrays.asList(surgery.get().getName(),optics.get().getId(),consultancy.get().getId()));
                       break;
                    case "Teeth Specialist":
                        consumableSpecializedField.setField(f);
                        consumableSpecializedField.setAppointmentTypes(Arrays.asList(surgery.get().getName(),dental.get().getId(),consultancy.get().getId()));
                        break;

                    default:
                        consumableSpecializedField.setField(f);
                        consumableSpecializedField.setAppointmentTypes(Arrays.asList(consultancy.get().getId()));


                }
                specializationFieldsService.createSpecializationField(consumableSpecializedField);
                log.info("REGISTER SPECIALIZATION FIELD {}    done", f);
            }



        }
    }


    public void blacklistNonWorkingHours() {

        BlacklistedTimeRange morningTime = new BlacklistedTimeRange();
        morningTime.setStartTime(LocalTime.of(00, 00));
        morningTime.setEndTime(LocalTime.of(07, 59));

        BlacklistedTimeRange eveningTime = new BlacklistedTimeRange();
        eveningTime.setStartTime(LocalTime.of(18, 00));
        eveningTime.setEndTime(LocalTime.of(23, 59));

        if (!blacklistedTimeRangeRepository.existsByStartTimeAndEndTime(morningTime.getStartTime(), morningTime.getEndTime())) {
            blacklistedTimeRangeRepository.save(morningTime);
            log.info("CREATE TIME BLACKLIST: [MORNING]    done");
        }

        if (!blacklistedTimeRangeRepository.existsByStartTimeAndEndTime(eveningTime.getStartTime(), eveningTime.getEndTime())) {
            blacklistedTimeRangeRepository.save(eveningTime);
            log.info("CREATE TIME BLACKLIST: [EVENING]    done");
        }

    }
}
