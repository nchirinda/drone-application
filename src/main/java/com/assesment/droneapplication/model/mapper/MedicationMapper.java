package com.assesment.droneapplication.model.mapper;

import com.assesment.droneapplication.model.dto.MedicationDto;
import com.assesment.droneapplication.model.entity.Medication;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MedicationMapper {
    Medication medicationDtoToMedication(MedicationDto medicationDto);

    MedicationDto medicationToMedicationDto(Medication medication);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Medication updateMedicationFromMedicationDto(MedicationDto medicationDto, @MappingTarget Medication medication);
}
