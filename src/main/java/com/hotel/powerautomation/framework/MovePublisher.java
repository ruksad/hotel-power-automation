package com.hotel.powerautomation.framework;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.model.SubCorridor;
import lombok.Data;

@Data
public class MovePublisher implements Subject {

    private List<SubCorridor> observers = new ArrayList<>();

    @Override
    public void attach(SubCorridor o) {
        observers.add(o);
    }

    @Override
    public void detach(SubCorridor o) {
        observers.remove(o);
    }

    @Override
    public void notifyUpdate(SubCorridor subCorridor, boolean isMovement) {

        for (SubCorridor o : observers) {

            o.update(subCorridor, isMovement);

        }
    }
}
