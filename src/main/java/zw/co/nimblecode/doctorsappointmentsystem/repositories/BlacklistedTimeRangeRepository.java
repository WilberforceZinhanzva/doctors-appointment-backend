package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.BlacklistedTimeRange;

public interface BlacklistedTimeRangeRepository extends JpaRepository<BlacklistedTimeRange,String> {
}
