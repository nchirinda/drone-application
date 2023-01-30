package com.assesment.droneapplication.service;

import com.assesment.droneapplication.model.Medication;
import com.assesment.droneapplication.model.dto.DroneDto;
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

    DroneDto loadDrone(List<Medication> medicationList);

    List<DroneDto> getAvailableDrones();

    String getDroneBatteryLevel(UUID droneId);
}
