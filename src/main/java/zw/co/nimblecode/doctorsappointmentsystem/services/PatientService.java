package zw.co.nimblecode.doctorsappointmentsystem.services;

import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Patient;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferablePatient;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<TransferablePatient> patients() {
        return patientRepository.findAll().stream().map(Patient::serializeForTransfer).collect(Collectors.toList());
    }
}
