package com.assesment.droneapplication.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Entity
@Table(name = "medications")
public class Medication extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private double weight;

    private String code;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @NotNull
    @JoinColumn(nullable = false)
    private MedicationImage image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MedicationImage getImage() {
        return image;
    }

    public void setImage(MedicationImage image) {
        this.image = image;
    }
}
