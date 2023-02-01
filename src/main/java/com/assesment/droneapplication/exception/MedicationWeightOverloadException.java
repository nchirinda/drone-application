package com.assesment.droneapplication.exception;

import lombok.Getter;

/**
 * @author Nyasha Chirinda - 01/02/2023
 */

@Getter
public class MedicationWeightOverloadException extends RuntimeException {

    private final double medItemsWeight;
    private final double droneWeightLimit;

    public MedicationWeightOverloadException(double medItemsWeight, double droneWeightLimit) {
        super("Total medication weight is excess: %sgr, drone can carry at most: %sgr".formatted(medItemsWeight, droneWeightLimit));
        this.medItemsWeight = medItemsWeight;
        this.droneWeightLimit = droneWeightLimit;
    }
}
