package com.assesment.droneapplication.model.payload;

import com.assesment.droneapplication.model.dto.MedicationImageDto;
import com.assesment.droneapplication.model.validators.UniqueMedicationCode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Nyasha Chirinda - 31/01/2023
 */

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMedicationReq {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\d\\-\\_]+", message = "name can only be a combination of letters, numbers and special characters _ and -")
    private String name;

    @Max(value = 500, message = "maximum weight allowed for a drone is 500gr")
    private double weight;

    @NotBlank
    @Pattern(regexp = "[A-Z\\_\\d]+", message = "code can only be a combination of upper case letters, underscore and numbers")
    @UniqueMedicationCode
    private String code;

    private MedicationImageDto image;
}
