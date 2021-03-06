package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import static com.hotel.powerautomation.model.abstractmodels.Device.getDevice;
import com.hotel.powerautomation.framework.Observer;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.abstractmodels.Device;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCorridor implements Corridor, Observer {

    private String subCorridorName;
    private List<Device> devices;


    @Override
    public List<Device> getDevices() {
        return this.devices;
    }

    public static List<Corridor> createCorridor(String floorName, int noOfCorridor) {
        List<Corridor> mc = new ArrayList<>();
        for (int k = 0; k < noOfCorridor; k++) {
            final SubCorridor subCorridor = new SubCorridor();
            subCorridor.setSubCorridorName(floorName + "Sub corridor " + (k + 1));
            final List<Device> device = getDevice();
            switchOffTheLights(device);
            subCorridor.setDevices(device);
            mc.add(subCorridor);
        }
        return mc;
    }

    private static void switchOffTheLights(List<Device> devices) {
        devices.forEach(x -> {
            if (x instanceof Light) {
                x.action(false);
            }
        });
    }

    public static SubCorridor findSubCorridor(List<Corridor> subCorridors, String subCorridorName) {
        return subCorridors.stream().map(x -> (SubCorridor) x)
            .filter(x -> x.getSubCorridorName().equals(subCorridorName)).findFirst().get();
    }

    @Override
    public void update(SubCorridor subCorridor, boolean isMovement) {
        if (this.equals(subCorridor) && isMovement) {
            for (Device device : subCorridor.getDevices()) {
                device.action(true);
            }

        } else if (isMovement) {
            for (Device device : this.getDevices()) {
                device.action(false);
            }
        } else {
            defaultStateDefaultToCorridor(subCorridor);
        }
    }

    private void defaultStateDefaultToCorridor(SubCorridor subCorridor) {
        for (Device device : subCorridor.getDevices()) {
            if (device instanceof AirConditioner) {
                device.action(true);
            }
            if (device instanceof Light) {
                device.action(false);
            }
        }
    }

    @Override
    public void updatePowerConsumption(SubCorridor subCorridor, boolean flag) {
        if (this.equals(subCorridor)) {
            return;
        }
        if (flag) {

            for (Device device : this.getDevices()) {
                device.action(false);
            }
        } else {

            for (Device device : this.getDevices()) {
                if (device instanceof Light) {
                    device.action(false);
                } else if (device instanceof AirConditioner) {
                    device.action(true);
                }
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(subCorridorName);
        sb.append(devices.toString());
        return sb.toString();
    }
}
