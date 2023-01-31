package com.assesment.droneapplication.model;

import com.assesment.droneapplication.model.enums.DroneModel;
import com.assesment.droneapplication.model.enums.DroneState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Entity
@Table(name = "drones")
public class Drone extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String serialNumber;

    @Enumerated
    @Column(nullable = false)
    private DroneModel model;

    private double weightLimit;

    private int batteryCapacity;

    @Enumerated
    @Column(nullable = false)
    private DroneState state;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}
