package com.assesment.droneapplication.service;

import com.assesment.droneapplication.exception.InsufficientBatteryCapacityException;
import com.assesment.droneapplication.exception.ResourceNotFoundException;
import com.assesment.droneapplication.exception.MedicationWeightOverloadException;
import com.assesment.droneapplication.model.dto.DroneDto;
import com.assesment.droneapplication.model.dto.MedicationDto;
import com.assesment.droneapplication.model.entity.Drone;
import com.assesment.droneapplication.model.entity.Medication;
import com.assesment.droneapplication.model.enums.DroneModel;
import com.assesment.droneapplication.model.enums.DroneState;
import com.assesment.droneapplication.model.mapper.DroneMapper;
import com.assesment.droneapplication.model.mapper.MedicationMapper;
import com.assesment.droneapplication.model.payload.RegisterDroneReq;
import com.assesment.droneapplication.repository.DroneRepository;
import com.assesment.droneapplication.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.assesment.droneapplication.utils.AppConstants.MIN_BATTERY_CAPACITY;
import static com.assesment.droneapplication.utils.FormatterUtil.toJson;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneMapper droneMapper;
    private final MedicationMapper medicationMapper;
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
    public void loadDrone(UUID droneId, List<MedicationDto> medicationItems) {

        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Load Drone", "Drone", "ID", droneId));

        determineIfEnoughBatteryCapacity(drone);

        validateLoadWeight(drone, medicationItems);

        List<Medication> medications = medicationItems.stream()
                .map(medicationMapper::medicationDtoToMedication)
                .toList();

        drone.getMedicationItems().addAll(medications);
        drone.setState(DroneState.LOADED);

        log.info("Updated Drone: {}", toJson(drone));

        droneRepository.save(drone);
    }

    private void determineIfEnoughBatteryCapacity(Drone drone) {
        if (drone.getBatteryCapacity() < MIN_BATTERY_CAPACITY) {
            throw new InsufficientBatteryCapacityException(drone.getBatteryCapacity(), MIN_BATTERY_CAPACITY + "%");
        }
    }

    private void validateLoadWeight(Drone drone, List<MedicationDto> medications) {
        double totalMedItemsWeight = medications.stream()
                .mapToDouble(MedicationDto::getWeight).sum();

        if (totalMedItemsWeight > drone.getWeightLimit()) {
            throw new MedicationWeightOverloadException(totalMedItemsWeight, drone.getWeightLimit());
        }
    }

    @Override
    public void unloadDrone(UUID droneId, List<MedicationDto> medicationItems) {

        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Unload Drone", "Drone", "ID", droneId));

        List<Medication> medications = new java.util.ArrayList<>(medicationItems.stream()
                .map(medicationMapper::medicationDtoToMedication)
                .toList());

        drone.getMedicationItems().removeAll(medications);

        if (drone.getMedicationItems().isEmpty()) {
            drone.setState(DroneState.DELIVERED);
        } else {
            drone.setState(DroneState.DELIVERING);
        }
        droneRepository.save(drone);
    }

    @Override
    public List<MedicationDto> getLoadedMedication(UUID droneId) {

        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Get Loaded Medication", "Drone", "ID", droneId));

        return drone.getMedicationItems().stream()
                .map(medicationMapper::medicationToMedicationDto)
                .toList();
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
