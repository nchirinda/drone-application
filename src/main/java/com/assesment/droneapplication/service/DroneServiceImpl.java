package com.assesment.droneapplication.service;

import com.assesment.droneapplication.exception.InsufficientBatteryCapacityException;
import com.assesment.droneapplication.exception.MedicationWeightOverloadException;
import com.assesment.droneapplication.exception.ResourceNotFoundException;
import com.assesment.droneapplication.model.dto.DroneDto;
import com.assesment.droneapplication.model.dto.MedicationDto;
import com.assesment.droneapplication.model.entity.BatteryAuditLog;
import com.assesment.droneapplication.model.entity.Drone;
import com.assesment.droneapplication.model.entity.Medication;
import com.assesment.droneapplication.model.enums.BatteryLevel;
import com.assesment.droneapplication.model.enums.DroneModel;
import com.assesment.droneapplication.model.enums.DroneState;
import com.assesment.droneapplication.model.mapper.DroneMapper;
import com.assesment.droneapplication.model.mapper.MedicationMapper;
import com.assesment.droneapplication.model.payload.RegisterDroneReq;
import com.assesment.droneapplication.repository.BatteryAuditLogRepository;
import com.assesment.droneapplication.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final BatteryAuditLogRepository batteryAuditLogRepository;

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

        log.info("Registering new drone: {}", toJson(registerDroneReq));

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
        return droneRepository.getAvailableDronesForLoading().stream()
                .map(droneMapper::droneToDroneDto)
                .toList();
    }

    /**
     * Scheduler to run after the previous scheduler job has completed, default 15 minutes
     * After the application startup, it will delay to run until after the specified initialDelay, default 5 minutes
     */
    @Scheduled(fixedDelayString = "${audit.battery-level.fixed-rate:90000}", initialDelayString = "${audit.battery-level.initial-delay:30000}")
    public void auditBatteryCapacity() {

        long startTime = System.currentTimeMillis();

        log.info("Audit Drone BatteryLevel task STARTED");

        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {

            BatteryAuditLog auditLog = new BatteryAuditLog();
            auditLog.setDroneId(drone.getId());
            auditLog.setBatteryCapacity(drone.getBatteryCapacity());
            auditLog.setAuditDateTime(LocalDateTime.now());
            auditLog.setBatteryLevel(determineBatteryLevel(drone.getBatteryCapacity()));

            batteryAuditLogRepository.save(auditLog);
        }

        long endTime = System.currentTimeMillis();

        log.info("Audit Drone BatteryLevel task COMPLETED, Total processing time [{}]-seconds ", (endTime - startTime) / 1000.0);
    }

    private BatteryLevel determineBatteryLevel(int batteryCapacity) {
        if (batteryCapacity == 0) {
            return BatteryLevel.EMPTY;
        } else if (batteryCapacity < 25) {
            return BatteryLevel.LOW;
        } else if (batteryCapacity > 25 && batteryCapacity < 100) {
            return BatteryLevel.OKAY;
        } else {
            return BatteryLevel.FULL;
        }
    }

    @Override
    public int getDroneBatteryLevel(UUID droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Get Drone Battery Level", "Drone", "ID", droneId));

        return drone.getBatteryCapacity();
    }

    DroneModel determineDroneModel(double weight) {
        if (weight <= 100.0) {
            return DroneModel.LIGHT_WEIGHT;
        } else if (weight <= 200.0) {
            return DroneModel.MIDDLE_WEIGHT;
        } else if (weight <= 350.0) {
            return DroneModel.CRUISER_WEIGHT;
        } else {
            return DroneModel.HEAVY_WEIGHT;
        }
    }

}
