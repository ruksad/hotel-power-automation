package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.abstractmodels.Device;
import lombok.Data;

@Data
public class MainCorridor implements Corridor {

    private String mainCorridorName;
    private List<Device> devices;

    @Override
    public List<Device> getDevices() {
        return this.devices;
    }

    public static List<Corridor> createCorridor(int noOfCorridor) {
        List<Corridor> subC = new ArrayList<>();
        for (int j = 0; j < noOfCorridor; j++) {
            final MainCorridor mainCorridor = new MainCorridor();
            mainCorridor.setMainCorridorName("Main corridor " + (j + 1));
            final List<Device> device = Device.getDevice();
            mainCorridor.setDevices(device);
            subC.add(mainCorridor);
        }
        return subC;
    }
}
