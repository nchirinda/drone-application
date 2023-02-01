package com.assesment.droneapplication.repository;

import com.assesment.droneapplication.model.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DroneRepository extends JpaRepository<Drone, UUID> {

    boolean existsBySerialNumber(String serialNumber);

    @Query("SELECT d FROM Drone d WHERE d.state = 1 AND d.batteryCapacity >= 25")
    List<Drone> getAvailableDronesForLoading();

}