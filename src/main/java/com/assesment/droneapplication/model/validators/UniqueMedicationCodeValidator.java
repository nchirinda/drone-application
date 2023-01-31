package com.assesment.droneapplication.model.validators;

import com.assesment.droneapplication.repository.MedicationRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Nyasha Chirinda - 31/01/2023
 */

public class UniqueMedicationCodeValidator implements ConstraintValidator<UniqueMedicationCode, String> {

    private final MedicationRepository medicationRepository;

    public UniqueMedicationCodeValidator(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public boolean isValid(String medicationCode, ConstraintValidatorContext context) {
        return !medicationRepository.existsByCode(medicationCode);
    }
}

