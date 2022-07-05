package zw.co.nimblecode.doctorsappointmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.Credentials;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials, String> {
    boolean existsByUsername(String username);

    Optional<Credentials> findByUsername(String username);
}
