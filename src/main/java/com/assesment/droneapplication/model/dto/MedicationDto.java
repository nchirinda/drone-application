package com.assesment.droneapplication.model.dto;

import com.assesment.droneapplication.model.entity.Medication;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A DTO for the {@link Medication} entity
 */
@Data
public class MedicationDto implements Serializable {
    private final UUID id;
    private final LocalDateTime createdDateTime;
    private final String name;
    private final double weight;
    private final String code;
    private final String image;
}