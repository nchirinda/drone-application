package com.assesment.droneapplication.controller;

import com.assesment.droneapplication.model.dto.MedicationDto;
import com.assesment.droneapplication.model.payload.RegisterMedicationReq;
import com.assesment.droneapplication.service.MedicationService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/medication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicationController {

    private final MedicationService medicationService;

    @GetMapping
    public ResponseEntity<List<MedicationDto>> getAll() {
        List<MedicationDto> resources = medicationService.findAll();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(medicationService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<MedicationDto> registerMedication(@Valid @RequestBody RegisterMedicationReq registerMedicationReq) {

        MedicationDto createdMedication = medicationService.registerMedication(registerMedicationReq);

        URI resourceLocation = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/v1/medication")
                .path("/{id}")
                .buildAndExpand(createdMedication.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).body(createdMedication);
    }
}
