package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import static com.hotel.powerautomation.model.abstractmodels.Device.getDevice;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.abstractmodels.Device;
import lombok.Data;

@Data
public class SubCorridor implements Corridor {

    private String subCorridorName;
    private List<Device> devices;

    @Override
    public List<Device> getDevices() {
        return this.devices;
    }

    public static List<Corridor> createCorridor(int noOfCorridor) {
        List<Corridor> mc = new ArrayList<>();
        for (int k = 0; k < noOfCorridor; k++) {
            final SubCorridor subCorridor = new SubCorridor();
            subCorridor.setSubCorridorName("Sub corridor " + (k + 1));
            final List<Device> device = getDevice();
            subCorridor.setDevices(device);
            mc.add(subCorridor);
        }
        return mc;
    }
}
