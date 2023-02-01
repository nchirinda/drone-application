package com.assesment.droneapplication.model.validators;

import com.assesment.droneapplication.repository.DroneRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Nyasha Chirinda - 31/01/2023
 */

public class UniqueSerialNumberValidator implements ConstraintValidator<UniqueSerialNumber, String> {

    private final DroneRepository droneRepository;

    public UniqueSerialNumberValidator(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public boolean isValid(String serialNumber, ConstraintValidatorContext context) {
        return !droneRepository.existsBySerialNumber(serialNumber);
    }
}

