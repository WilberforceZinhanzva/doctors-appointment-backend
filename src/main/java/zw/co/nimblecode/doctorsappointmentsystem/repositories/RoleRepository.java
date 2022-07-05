package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByRoleIgnoreCase(String role);

    Optional<Role> findByRole(String role);
}
