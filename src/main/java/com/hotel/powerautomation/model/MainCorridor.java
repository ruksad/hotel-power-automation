package com.hotel.powerautomation.model;

import java.util.List;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.abstractmodels.Device;
import lombok.Data;

@Data
public class MainCorridor implements Corridor {

    private String corridorName;
    @Override
    public List<Device> getDevices() {
        return null;
    }
}
