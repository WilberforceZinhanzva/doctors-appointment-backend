package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Permission;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, String> {
    boolean existsByPermissionIgnoreCase(String permission);

    List<Permission> findAllByPermissionInIgnoreCase(Set<String> permissions);

    Optional<Permission> findByPermission(String permission);
}
