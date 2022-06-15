package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,String> {
    List<Doctor> findAllByFullnameContainsIgnoreCase(String name);
    List<Doctor> findAllByFieldOfSpecialization_FieldContainsIgnoreCase(String field);
}
