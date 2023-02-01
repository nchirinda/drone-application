package com.assesment.droneapplication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link com.assesment.droneapplication.model.entity.MedicationImage} entity
 */

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicationImageDto implements Serializable {
    private String name;
    private String type;
    private String bytes;
}