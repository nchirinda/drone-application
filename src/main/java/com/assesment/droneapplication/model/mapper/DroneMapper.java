package com.assesment.droneapplication.model.mapper;

import com.assesment.droneapplication.model.Drone;
import com.assesment.droneapplication.model.dto.DroneDto;
import com.assesment.droneapplication.model.payload.RegisterDroneReq;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DroneMapper {
    Drone droneDtoToDrone(DroneDto droneDto);

    DroneDto droneToDroneDto(Drone drone);

    Drone droneReqToDrone(RegisterDroneReq registerDroneReq);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Drone updateDroneFromDroneDto(DroneDto droneDto, @MappingTarget Drone drone);
}
