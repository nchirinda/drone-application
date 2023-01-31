package com.assesment.droneapplication.model.dto;

import com.assesment.droneapplication.model.enums.DroneModel;
import com.assesment.droneapplication.model.enums.DroneState;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A DTO for the {@link com.assesment.droneapplication.model.Drone} entity
 */
@Data
public class DroneDto implements Serializable {
    private final UUID id;
    private final LocalDateTime createdDateTime;
    private final String serialNumber;
    private final DroneModel model;
    private final double weightLimit;
    private final int batteryCapacity;
    private final DroneState state;
}