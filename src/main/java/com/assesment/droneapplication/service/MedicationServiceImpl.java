package com.assesment.droneapplication.service;

import com.assesment.droneapplication.exception.ResourceNotFoundException;
import com.assesment.droneapplication.model.dto.MedicationDto;
import com.assesment.droneapplication.model.entity.Medication;
import com.assesment.droneapplication.model.mapper.MedicationMapper;
import com.assesment.droneapplication.model.payload.RegisterMedicationReq;
import com.assesment.droneapplication.repository.MedicationRepository;
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

    private final MedicationMapper medicationMapper;
    private final MedicationRepository medicationRepository;

    @Override
    public List<MedicationDto> findAll() {
        List<Medication> medication = medicationRepository.findAll();
        return medication.stream()
                .map(medicationMapper::medicationToMedicationDto)
                .toList();
    }

    @Override
    public MedicationDto findById(UUID id) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FindById", "Medication", "id", id));
        return medicationMapper.medicationToMedicationDto(medication);
    }

    @Override
    public MedicationDto registerMedication(RegisterMedicationReq registerMedicationReq) {
        log.info("Registering new medication: {}", registerMedicationReq);

        Medication newMedication = medicationMapper.medicationReqToMedication(registerMedicationReq);

        return medicationMapper.medicationToMedicationDto(medicationRepository.save(newMedication));
    }

    @Override
    public List<MedicationDto> getLoadedMedication(UUID medicationId) {
        return null;
    }
}
