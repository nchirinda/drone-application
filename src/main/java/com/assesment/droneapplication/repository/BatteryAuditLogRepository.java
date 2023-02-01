package com.assesment.droneapplication.repository;

import com.assesment.droneapplication.model.entity.BatteryAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BatteryAuditLogRepository extends JpaRepository<BatteryAuditLog, UUID> {
}