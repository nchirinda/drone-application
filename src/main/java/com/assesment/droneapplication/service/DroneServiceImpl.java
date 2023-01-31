package com.assesment.droneapplication.service;

import com.assesment.droneapplication.exception.ResourceNotFoundException;
import com.assesment.droneapplication.model.Drone;
import com.assesment.droneapplication.model.Medication;
import com.assesment.droneapplication.model.dto.DroneDto;
import com.assesment.droneapplication.model.enums.DroneModel;
import com.assesment.droneapplication.model.enums.DroneState;
import com.assesment.droneapplication.model.mapper.DroneMapper;
import com.assesment.droneapplication.model.payload.RegisterDroneReq;
import com.assesment.droneapplication.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneMapper droneMapper;
    private final DroneRepository droneRepository;

    @Override
    public List<DroneDto> findAll() {
        List<Drone> drones = droneRepository.findAll();
        return drones.stream()
                .map(droneMapper::droneToDroneDto)
                .toList();
    }

    @Override
    public DroneDto findById(UUID id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FindById", "Drone", "id", id));
        return droneMapper.droneToDroneDto(drone);
    }

    @Override
    public DroneDto registerDrone(RegisterDroneReq registerDroneReq) {

        log.info("Registering new drone: {}", registerDroneReq);

        Drone newDrone = droneMapper.droneReqToDrone(registerDroneReq);
        newDrone.setModel(determineDroneModel(registerDroneReq.getWeightLimit()));
        newDrone.setState(DroneState.IDLE);

        return droneMapper.droneToDroneDto(droneRepository.save(newDrone));
    }

    @Override
    public DroneDto loadDrone(List<Medication> medicationList) {
        return null;
    }

    @Override
    public List<DroneDto> getAvailableDrones() {
        return null;
    }

    @Override
    public String getDroneBatteryLevel(UUID droneId) {
        return null;
    }

    DroneModel determineDroneModel(int weight) {
        if (weight <= 100) {
            return DroneModel.LIGHT_WEIGHT;
        } else if (weight <= 200) {
            return DroneModel.MIDDLE_WEIGHT;
        } else if (weight <= 350) {
            return DroneModel.CRUISER_WEIGHT;
        } else {
            return DroneModel.HEAVY_WEIGHT;
        }
    }

}
