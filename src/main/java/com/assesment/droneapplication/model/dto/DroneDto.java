package com.assesment.droneapplication.model.dto;

import com.assesment.droneapplication.model.enums.DroneModel;
import com.assesment.droneapplication.model.enums.DroneState;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A DTO for the {@link com.assesment.droneapplication.model.Drone} entity
 */
public record DroneDto(
        UUID id,
        LocalDateTime createdDateTime,
        String serialNumber,
        DroneModel model,
        int weightLimit,
        double batteryCapacity,
        DroneState state) implements Serializable {
}