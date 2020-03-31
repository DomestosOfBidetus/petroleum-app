package petroleum.application.petroleum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewDistanceRequest {

    @NotNull(message = "Distance can not be empty")
    private Double distance;

    @NotNull(message = "Driver can not be empty")
    private String driver;

    Double getDistance() {
        return distance;
    }

    String getDriver() {
        return driver;
    }
}
