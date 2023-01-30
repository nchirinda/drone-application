package com.assesment.droneapplication.service;

import com.assesment.droneapplication.model.dto.MedicationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class MedicationServiceImpl implements MedicationService {

    @Override
    public List<MedicationDto> getLoadedMedication(UUID droneId) {
        return null;
    }
}
