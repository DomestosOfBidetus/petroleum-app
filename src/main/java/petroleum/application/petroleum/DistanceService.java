package petroleum.application.petroleum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class DistanceService implements AddNewDistance {

    @Autowired
    private DistanceRepository distanceRepository;

    @Autowired
    private TankRepository tankRepository;

    @Override
    public void createNewDistance(NewDistanceRequest newDistanceRequest) {
        Distance distance = buildNewDistance(newDistanceRequest);
        distanceRepository.save(distance);
    }

    private Distance buildNewDistance(NewDistanceRequest newDistanceRequest) {
        Tank activeTank = tankRepository.findByActiveTrue().get();
        return Distance.builder()
                .driver(newDistanceRequest.getDriver())
                .distance(newDistanceRequest.getDistance())
                .date(LocalDateTime.now())
                .tank(activeTank)
                .build();
    }
}
