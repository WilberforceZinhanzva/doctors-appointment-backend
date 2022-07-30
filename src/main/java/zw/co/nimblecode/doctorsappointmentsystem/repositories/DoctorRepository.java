package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Doctor;

import java.util.List;
import java.util.Set;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    List<Doctor> findAllByFullnameContainsIgnoreCase(String name);

    List<Doctor> findAllByFieldOfSpecialization_FieldIgnoreCase(String field);

    List<Doctor> findAllByFieldOfSpecialization_AppointmentTypes_NameIgnoreCase(String name);
}
