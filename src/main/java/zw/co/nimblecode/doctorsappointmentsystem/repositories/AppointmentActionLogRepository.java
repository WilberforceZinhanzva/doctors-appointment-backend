package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.AppointmentActionLog;

public interface AppointmentActionLogRepository extends JpaRepository<AppointmentActionLog, String> {
}
