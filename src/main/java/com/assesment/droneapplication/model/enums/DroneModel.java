package com.assesment.droneapplication.model.enums;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

public enum DroneModel {

    LIGHT_WEIGHT("Lightweight"), 
    MIDDLE_WEIGHT("Middleweight"),
    CRUISER_WEIGHT("Cruiserweight"),
    HEAVY_WEIGHT("Heavyweight");

    private final String weight;

    DroneModel(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString()
    {
        return weight;
    }

}
