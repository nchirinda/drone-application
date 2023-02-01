package com.assesment.droneapplication.model.entity;

import com.assesment.droneapplication.model.enums.BatteryLevel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Nyasha Chirinda - 01/02/2023
 */

@Entity
@Table(name = "battery_audit_logs")
public class BatteryAuditLog extends BaseEntity {

    private UUID droneId;

    private BatteryLevel batteryLevel;

    private int batteryCapacity;
    private LocalDateTime auditDateTime;

    public UUID getDroneId() {
        return droneId;
    }

    public void setDroneId(UUID droneId) {
        this.droneId = droneId;
    }

    public BatteryLevel getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(BatteryLevel batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public LocalDateTime getAuditDateTime() {
        return auditDateTime;
    }

    public void setAuditDateTime(LocalDateTime auditDateTime) {
        this.auditDateTime = auditDateTime;
    }
}
