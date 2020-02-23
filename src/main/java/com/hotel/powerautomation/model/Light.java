package com.hotel.powerautomation.model;

import com.hotel.powerautomation.model.abstractmodels.Device;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Light implements Device {

    //TODO this should be added the property file
    public static final int LIGHT_POWER_UNITS = 5;

    private boolean state;

    public Light() {
        this.state = true;
    }

    @Override
    public void action(boolean turnOn) {
        state = turnOn;
    }

    @Override
    public int units() {
        return LIGHT_POWER_UNITS;
    }

    @Override
    public boolean state() {
        return state;
    }

    @Override
    public String stateToString() {
        return this.state?"ON":"OFF";
    }
}
