package com.assesment.droneapplication.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Nyasha Chirinda - 01/02/2023
 */

@Entity
@Table(name = "medication_images")
public class MedicationImage extends BaseEntity {

    private String name;

    private String type;

    private String bytes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }
}
