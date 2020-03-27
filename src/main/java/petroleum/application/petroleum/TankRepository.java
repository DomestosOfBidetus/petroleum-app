package petroleum.application.petroleum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {
    Optional<Tank> findByActiveTrue();
}
