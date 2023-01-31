package com.assesment.droneapplication.model.entity;

import com.assesment.droneapplication.model.enums.DroneModel;
import com.assesment.droneapplication.model.enums.DroneState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

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

    @ManyToMany(cascade = {PERSIST, MERGE, REMOVE})
    @JoinTable(
            name = "drone_medication",
            joinColumns = {@JoinColumn(name = "drone_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "medication_id", referencedColumnName = "id")})
    private List<Medication> medicationItems = new ArrayList<>();

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

    public List<Medication> getMedicationItems() {
        return medicationItems;
    }

    public void setMedicationItems(List<Medication> medicationItems) {
        this.medicationItems = medicationItems;
    }
}
