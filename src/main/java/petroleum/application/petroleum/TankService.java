package petroleum.application.petroleum;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TankService implements AddNewTank, GetTankInfo{

    private final TankRepository tankRepository;

    private final DistanceRepository distanceRepository;

    public TankService(TankRepository tankRepository,
            DistanceRepository distanceRepository) {
        this.tankRepository = tankRepository;
        this.distanceRepository = distanceRepository;
    }

    @Override
    public String createNewTank(NewTankRequest newTankRequest) {
        String settlementMessage;
        Optional<Long> previousTankId = dismissPreviousTank();
        settlementMessage = previousTankId.map(this::calculateDue).orElse("Tank created");
        Tank tank = buildNewTank(newTankRequest);
        tankRepository.save(tank);
        return settlementMessage;
    }

    @Override
    public List<String> getTankInfo(Long tankQuantity) {
        List<Tank> allTanks = tankRepository.findAll();
        List<String> returnMessage = new ArrayList<>();
        allTanks.forEach(tank -> returnMessage.add(tank.getId() + "/" + tank.getActive() + "/" + tank.getAmountDue() + "/" + tank.getClosingDate() + "/" + tank.getCreationDate() + "/" + tank.getPayor() + "/" + tank.getUnitPrice() + "/" + tank.getVolume()));
        return returnMessage;
    }

    private String calculateDue(Long previousTankId) {
        Tank tank = tankRepository.findById(previousTankId).get();
        Double unitPrice = tank.getUnitPrice();
        Double volume = tank.getVolume();
        String payor = tank.getPayor();
        List<Distance> distancesByTankAndDriver = distanceRepository.findDistancesByTankAndDriver(tank, payor);
        List<Distance> distancesByTankAndDriverNot = distanceRepository.findDistancesByTankAndDriverNot(tank, payor);
        Double payorDistance = sumDistances(distancesByTankAndDriver);
        Double otherDistance = sumDistances(distancesByTankAndDriverNot);
        Double otherCost = otherDistance * ((unitPrice * volume) / (otherDistance + payorDistance));
        Double roundedOtherCost = new BigDecimal(Double.toString(otherCost)).setScale(2, RoundingMode.FLOOR).doubleValue();
        tank.setAmountDue(roundedOtherCost);
        tank.setClosingDate(LocalDateTime.now());

        return "Settlement for tank no. " + previousTankId + ": \n\tPayor: " + payor + ", \nAmount due: " + roundedOtherCost;
    }

    private Double sumDistances(List<Distance> distances) {
        return distances.stream()
                .mapToDouble(Distance::getDistance)
                .sum();
    }

    private Optional<Long> dismissPreviousTank() {
        Optional<Tank> previousTank = tankRepository.findByActiveTrue();
        if (previousTank.isPresent()){
            previousTank.get().deactivateTank();
            return Optional.of(previousTank.get().getId());
        }
        else {
            return Optional.empty();
        }
    }

    private Tank buildNewTank(NewTankRequest newTankRequest) {
        return Tank.builder()
                .volume(newTankRequest.getVolume())
                .unitPrice(newTankRequest.getUnitPrice())
                .payor(newTankRequest.getPayor())
                .active(true)
                .distances(new ArrayList<>())
                .creationDate(LocalDateTime.now())
                .build();
    }

}
