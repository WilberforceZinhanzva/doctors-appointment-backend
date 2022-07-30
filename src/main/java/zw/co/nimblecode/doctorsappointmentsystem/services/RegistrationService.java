package zw.co.nimblecode.doctorsappointmentsystem.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceAlreadyExistsException;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceNotFoundException;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableAssistant;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableDoctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumablePatient;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableAssistant;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableDoctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferablePatient;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RegistrationService {

    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private AssistantRepository assistantRepository;
    private CredentialsRepository credentialsRepository;
    private PasswordEncoder passwordEncoder;
    private SpecializationFieldRepository specializationFieldRepository;
    private RoleRepository roleRepository;


    public RegistrationService(DoctorRepository doctorRepository,
                               PatientRepository patientRepository,
                               AssistantRepository assistantRepository,
                               CredentialsRepository credentialsRepository,
                               SpecializationFieldRepository specializationFieldRepository,
                               PasswordEncoder passwordEncoder,
                               RoleRepository roleRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.assistantRepository = assistantRepository;
        this.credentialsRepository = credentialsRepository;
        this.specializationFieldRepository = specializationFieldRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public TransferableDoctor registerDoctor(ConsumableDoctor consumableDoctor) {
        if (credentialsRepository.existsByUsername(consumableDoctor.getUsername()))
            throw new ResourceAlreadyExistsException("Username already taken!");

        //Fetch Roles
        Optional<Role> doctorRole = roleRepository.findByRole("DOCTOR");
        if (doctorRole.isEmpty())
            throw new ResourceNotFoundException("Doctor role not found!");

        //Fetch Specialization Fields
        List<SpecializationField> specializationFields = specializationFieldRepository.findAllById(consumableDoctor.getFieldsOfSpecialization());

        Credentials credentials = new Credentials();
        credentials.setUsername(consumableDoctor.getUsername());
        credentials.setPassword(passwordEncoder.encode(consumableDoctor.getPassword()));
        credentials.getRoles().add(doctorRole.get());

        Doctor doctor = new Doctor();
        doctor.setFullname(consumableDoctor.getFullname());
        doctor.setCredentials(credentials);
        doctor.setFieldOfSpecialization(new ArrayList<>(specializationFields));
        doctor.setActive(true);


        return doctorRepository.save(doctor).serializeForTransfer();

    }

    public TransferablePatient registerPatient(ConsumablePatient consumablePatient) {
        if (credentialsRepository.existsByUsername(consumablePatient.getUsername()))
            throw new ResourceAlreadyExistsException("Username already taken!");

        //Fetch Roles
        Optional<Role> patientRole = roleRepository.findByRole("PATIENT");
        if (patientRole.isEmpty())
            throw new ResourceNotFoundException("Patient role not found!");

        Credentials credentials = new Credentials();
        credentials.setUsername(consumablePatient.getUsername());
        credentials.setPassword(passwordEncoder.encode(consumablePatient.getPassword()));
        credentials.getRoles().add(patientRole.get());

        Patient patient = new Patient();
        patient.setFullname(consumablePatient.getFullname());
        patient.setAddress(consumablePatient.getAddress());
        patient.setPhone(consumablePatient.getPhone());
        patient.setEmail(consumablePatient.getEmail());
        patient.setCredentials(credentials);

        return patientRepository.save(patient).serializeForTransfer();
    }

    public TransferableAssistant registerAssistant(ConsumableAssistant consumableAssistant) {
        if (credentialsRepository.existsByUsername(consumableAssistant.getUsername()))
            throw new ResourceAlreadyExistsException("Username already taken!");

        //Fetch roles
        Optional<Role> assistantRole = roleRepository.findByRole("ASSISTANT");
        if (assistantRole.isEmpty())
            throw new ResourceNotFoundException("Assistant role not found!");

        Credentials credentials = new Credentials();
        credentials.setUsername(consumableAssistant.getUsername());
        credentials.setPassword(passwordEncoder.encode(consumableAssistant.getPassword()));
        credentials.getRoles().add(assistantRole.get());

        Assistant assistant = new Assistant();
        assistant.setFullname(consumableAssistant.getFullname());
        assistant.setCredentials(credentials);

        return assistantRepository.save(assistant).serializeForTransfer();

    }
}
