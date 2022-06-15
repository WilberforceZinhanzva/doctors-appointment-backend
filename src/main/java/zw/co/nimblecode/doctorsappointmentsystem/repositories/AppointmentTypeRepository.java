package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentType;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, String> {
    boolean existsByNameIgnoreCase(String name);
}
