package com.hotel.powerautomation.model;

import java.util.List;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.abstractmodels.Device;
import lombok.Data;

@Data
public class SubCorridor implements Corridor {

    private String subCorridorName;
    @Override
    public List<Device> getDevices() {
        return null;
    }
}
