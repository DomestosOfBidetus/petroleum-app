package petroleum.application.petroleum;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("petroleum")
@AllArgsConstructor
public class Controller {

    public AddNewTank addNewTank;
    public AddNewDistance addNewDistance;
    public GetTankInfo getTankInfo;

    @RequestMapping("/test")
    public String index() {
        return "Greetings from Spring Boot Petroleum!";
    }

    @PostMapping("/add-new-tank")
    public ResponseEntity newTank(@RequestBody NewTankRequest newTankRequest){
        String settlementMessage = addNewTank.createNewTank(newTankRequest);
        return new ResponseEntity<>(settlementMessage, HttpStatus.OK);
    }

    @PostMapping("/add-new-distance")
    public ResponseEntity newDistance(@RequestBody NewDistanceRequest newDistanceRequest){
        addNewDistance.createNewDistance(newDistanceRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-tank-info")
    public ResponseEntity getTaskInfo(@RequestParam Long tankQuantity){
        List<String> tankInfo = getTankInfo.getTankInfo(tankQuantity);
        return new ResponseEntity<>(tankInfo, HttpStatus.OK);
    }

}
