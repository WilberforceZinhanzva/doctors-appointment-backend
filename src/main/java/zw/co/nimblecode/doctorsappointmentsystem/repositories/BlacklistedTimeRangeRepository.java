package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.BlacklistedTimeRange;

import java.time.LocalTime;

public interface BlacklistedTimeRangeRepository extends JpaRepository<BlacklistedTimeRange, String> {
    boolean existsByStartTime(LocalTime startTime);

    boolean existsByStartTimeAndEndTime(LocalTime startTime, LocalTime endTime);
}
