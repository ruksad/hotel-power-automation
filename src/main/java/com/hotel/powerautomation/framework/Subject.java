package com.hotel.powerautomation.framework;

import com.hotel.powerautomation.model.SubCorridor;

public interface Subject {

    void attach(SubCorridor o);

    void detach(SubCorridor o);

    void notifyUpdate(SubCorridor subCorridorName,boolean isMovement);
}
