package com.assesment.droneapplication.model.payload;

import com.assesment.droneapplication.model.validators.UniqueSerialNumber;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDroneReq {

    @NotBlank
    @Size(max = 100, message = "serialNumber maximum characters allowed is 100")
    @UniqueSerialNumber
    private String serialNumber;

    @Max(value = 500, message = "maximum weight allowed for a drone is 500.0gr")
    private double weightLimit;

    @Min(value = 0, message = "drone battery level can not go below 0%")
    @Max(value = 100, message = "drone battery level can not go above 100%")
    private int batteryCapacity;

}
