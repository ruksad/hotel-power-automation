package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import lombok.Data;

@Data
public class Floor {

    BiFunction<Integer, Integer, Integer> powerConsumptionFormula = (noOfMainCorridors, noOfSubCorridors) -> {
        return (noOfMainCorridors * 15) + (noOfSubCorridors * 10);
    };
    private String floorName;
    private int powerConsumption;

    private List<Corridor> mainCorridors = new ArrayList<>();
    private List<Corridor> subCorridors = new ArrayList<>();

    public int calculatePowerConsumption(int a, int b) {
        return powerConsumptionFormula.apply(a, b);
    }

    public int getMaxPowerConsumption() {
        int a = mainCorridors.size();
        int b = subCorridors.size();
        return calculatePowerConsumption(a, b);
    }
}
