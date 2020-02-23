package com.hotel.powerautomation.model.abstractmodels;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.model.AirConditioner;
import com.hotel.powerautomation.model.Light;

public interface Device {

    void action(boolean turnOn);

    int units();

    boolean state();
    String stateToString();

    static List<Device> getDevice() {
        List<Device> device = new ArrayList<>(4);
        final Light light = new Light();
        final AirConditioner ac = new AirConditioner();
        device.add(light);
        device.add(ac);
        return device;

    }

}
