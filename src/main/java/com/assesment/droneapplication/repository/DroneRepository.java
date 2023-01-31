package com.assesment.droneapplication.repository;

import com.assesment.droneapplication.model.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DroneRepository extends JpaRepository<Drone, UUID> {

    boolean existsBySerialNumber(String serialNumber);
}