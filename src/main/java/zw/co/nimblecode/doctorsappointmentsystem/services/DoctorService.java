package zw.co.nimblecode.doctorsappointmentsystem.services;

import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Doctor;
import zw.co.nimblecode.doctorsappointmentsystem.models.enums.DoctorSearchKey;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableDoctor;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<TransferableDoctor> doctors(DoctorSearchKey key, String searchValue) {
        List<Doctor> doctorList = new ArrayList<>();
        switch (key) {
            case Name:
                doctorList = doctorRepository.findAllByFullnameContainsIgnoreCase(searchValue);
                break;
            case SpecializationField:
                doctorList = doctorRepository.findAllByFieldOfSpecialization_FieldIgnoreCase(searchValue);
                break;
            case TypeOfAppointment:
                doctorList = doctorRepository.findAllByFieldOfSpecialization_AppointmentTypes_NameIgnoreCase(searchValue);
                break;
            default:
                doctorList = doctorRepository.findAll();
                break;
        }
        return doctorList.stream().map(Doctor::serializeForTransfer).collect(Collectors.toList());
    }
}
