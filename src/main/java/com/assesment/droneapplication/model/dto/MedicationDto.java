package com.assesment.droneapplication.model.dto;

import com.assesment.droneapplication.model.entity.Medication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A DTO for the {@link Medication} entity
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto implements Serializable {
    private UUID id;
    private LocalDateTime createdDateTime;
    private String name;
    private double weight;
    private String code;
    private MedicationImageDto image;
}