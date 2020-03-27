package petroleum.application.petroleum;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DistanceRepository extends JpaRepository<Distance, Long> {
    List<Distance> findAllByTank(Tank tank);

    List<Distance> findDistancesByTankAndDriverNot(Tank tank, String driver);
    List<Distance> findDistancesByTankAndDriver(Tank tank, String driver);

}
