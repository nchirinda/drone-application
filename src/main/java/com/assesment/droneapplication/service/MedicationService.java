package com.assesment.droneapplication.service;

import com.assesment.droneapplication.model.dto.MedicationDto;

import java.util.List;
import java.util.UUID;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

public interface MedicationService {
    List<MedicationDto> getLoadedMedication(UUID droneId);
}
