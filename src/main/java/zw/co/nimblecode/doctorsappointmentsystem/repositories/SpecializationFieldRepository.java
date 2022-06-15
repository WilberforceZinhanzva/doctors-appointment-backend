package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.SpecializationField;

public interface SpecializationFieldRepository extends JpaRepository<SpecializationField, String> {
    boolean existsByFieldIgnoreCase(String field);
}
