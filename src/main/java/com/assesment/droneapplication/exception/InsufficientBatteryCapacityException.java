package com.assesment.droneapplication.exception;

import lombok.Getter;

/**
 * @author Nyasha Chirinda - 01/02/2023
 */

@Getter
public class InsufficientBatteryCapacityException extends RuntimeException {

    private final int droneBatteryLevel;
    private final String minBatteryCapacity;

    public InsufficientBatteryCapacityException(int droneBatteryLevel, String minBatteryCapacity) {
        super("Drone BatteryCapacity is too low to be loaded current: %s should be at least: %s".formatted(droneBatteryLevel + "%", minBatteryCapacity));
        this.droneBatteryLevel = droneBatteryLevel;
        this.minBatteryCapacity = minBatteryCapacity;
    }
}
