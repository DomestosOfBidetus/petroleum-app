package petroleum.application.petroleum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;



import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewTankRequest {

    @NotNull(message = "Volume can not be empty")
    private Double volume;

    @NotNull(message = "Unit price can not be empty")
    private Double unitPrice;

    @NotNull(message = "Payor can not be empty")
    private String payor;

    Double getVolume() {
        return volume;
    }

    Double getUnitPrice() {
        return unitPrice;
    }

    String getPayor() {
        return payor;
    }

}

