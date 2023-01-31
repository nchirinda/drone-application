package com.assesment.droneapplication.model.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nyasha Chirinda - 31/01/2023
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueMedicationCodeValidator.class)
public @interface UniqueMedicationCode {

    String message() default "Medication with code already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
