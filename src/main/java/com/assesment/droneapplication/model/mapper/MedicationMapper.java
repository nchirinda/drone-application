package com.assesment.droneapplication.model.mapper;

import com.assesment.droneapplication.model.dto.MedicationImageDto;
import com.assesment.droneapplication.model.dto.MedicationDto;
import com.assesment.droneapplication.model.entity.Medication;
import com.assesment.droneapplication.model.entity.MedicationImage;
import com.assesment.droneapplication.model.payload.RegisterMedicationReq;
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

    Medication medicationReqToMedication(RegisterMedicationReq registerMedicationReq);

    MedicationImage medicationImageDtoToMedicationImage(MedicationImageDto medicationImageDto);

    MedicationImageDto medicationImageToMedicationImageDto(MedicationImage medicationImage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MedicationImage updateMedicationImageFromMedicationImageDto(MedicationImageDto medicationImageDto, @MappingTarget MedicationImage medicationImage);
}
