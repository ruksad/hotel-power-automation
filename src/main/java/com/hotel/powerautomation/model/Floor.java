package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.utils.Utils;
import lombok.Data;

@Data
public class Floor {

    private static BiFunction<Integer, Integer, Integer> powerConsumptionFormula = (noOfMainCorridors, noOfSubCorridors) -> {
        return (noOfMainCorridors * 15) + (noOfSubCorridors * 10);
    };

    private static Function<List<Corridor>, List<Integer>> extractDevicePowerConsumption = corridor -> {
        return corridor.stream().map(x -> {
            final List<Integer> ints = x.getDevices().stream().map(device -> device.units()).collect(Collectors.toList());
            return Utils.addList.apply(ints);
        }).collect(Collectors.toList());
    };

    private String floorName;
    private int powerConsumption;

    private List<Corridor> mainCorridors = new ArrayList<>();
    private List<Corridor> subCorridors = new ArrayList<>();


    public static int calculatePowerConsumption(int a, int b) {
        return powerConsumptionFormula.apply(a, b);
    }

    public static int getMaxPowerConsumption(Floor floor) {
        int a = floor.getMainCorridors().size();
        int b = floor.getSubCorridors().size();
        return calculatePowerConsumption(a, b);
    }

    public int currentPowerConsumption() {
        final List<Integer> mainCorridorPowerConsumption = extractDevicePowerConsumption.apply(this.mainCorridors);
        final List<Integer> subCorridorPowerConsumption = extractDevicePowerConsumption.apply(this.subCorridors);
        Integer mpc = Utils.addList.apply(mainCorridorPowerConsumption);
        Integer scpc = Utils.addList.apply(subCorridorPowerConsumption);
        return mpc + scpc;
    }
}
