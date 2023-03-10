package com.assesment.droneapplication.service;

import com.assesment.droneapplication.model.dto.DroneDto;
import com.assesment.droneapplication.model.dto.MedicationDto;
import com.assesment.droneapplication.model.payload.RegisterDroneReq;

import java.util.List;
import java.util.UUID;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

public interface DroneService {

    List<DroneDto> findAll();

    DroneDto findById(UUID id);

    DroneDto registerDrone(RegisterDroneReq registerDroneReq);

    void loadDrone(UUID droneId, List<MedicationDto> medicationItems);

    void unloadDrone(UUID id, List<MedicationDto> registerMedicationReqs);

    List<MedicationDto> getLoadedMedication(UUID droneId);

    List<DroneDto> getAvailableDrones();

    int getDroneBatteryLevel(UUID droneId);
}
