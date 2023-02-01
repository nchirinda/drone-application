package com.assesment.droneapplication.repository;

import com.assesment.droneapplication.model.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {
    boolean existsByCode(String medicationCode);
}