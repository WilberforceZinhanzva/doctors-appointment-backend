package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentTime;

public interface AppointmentTimeRepository extends JpaRepository<AppointmentTime, String> {
}
