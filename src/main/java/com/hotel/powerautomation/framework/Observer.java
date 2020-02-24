package com.hotel.powerautomation.framework;

import com.hotel.powerautomation.model.SubCorridor;

public interface Observer {

    public void update(SubCorridor subCorridor,boolean isMovement);
    public void updatePowerConsumption(SubCorridor subCorridor,boolean flag);
}
