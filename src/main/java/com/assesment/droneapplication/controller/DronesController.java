package com.assesment.droneapplication.controller;

import com.assesment.droneapplication.model.Medication;
import com.assesment.droneapplication.model.dto.DroneDto;
import com.assesment.droneapplication.model.payload.RegisterDroneReq;
import com.assesment.droneapplication.service.DroneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/drones", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class DronesController {

    public final DroneService droneService;

    @GetMapping
    public ResponseEntity<List<DroneDto>> getAll() {
        List<DroneDto> resources = droneService.findAll();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DroneDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(droneService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<DroneDto> registerDrone(@Valid @RequestBody RegisterDroneReq registerDroneReq) {
        return ResponseEntity.ok(droneService.registerDrone(registerDroneReq));
    }

    @PostMapping("/load_medication")
    public ResponseEntity<DroneDto> loadDrone(@RequestBody List<Medication> medicationList) {
        return ResponseEntity.ok(droneService.loadDrone(medicationList));
    }

    @GetMapping("/available_drones")
    public ResponseEntity<List<DroneDto>> getAvailableDrones() {
        return ResponseEntity.ok(droneService.getAvailableDrones());
    }

    @GetMapping("/{droneId}/battery_level")
    public ResponseEntity<String> getDroneBatteryLevel(@PathVariable UUID droneId) {
        return ResponseEntity.ok(droneService.getDroneBatteryLevel(droneId));
    }
}
