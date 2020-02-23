package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.input.InPut;
import com.hotel.powerautomation.model.utils.Utils;
import lombok.Data;

@Data
public class Floor {

    private static BinaryOperator<Integer> powerConsumptionFormula = (noOfMainCorridors, noOfSubCorridors) -> {
        return (noOfMainCorridors * 15) + (noOfSubCorridors * 10);
    };

    private static Function<List<Corridor>, List<Integer>> extractDevicePowerConsumption = corridor -> {
        return corridor.stream().map(x -> {
            final List<Integer> ints = x.getDevices().stream().filter(device -> device.state()).map(device -> device.units()).collect(Collectors.toList());
            return Utils.addList.apply(ints);
        }).collect(Collectors.toList());
    };

    private String floorName;
    private int powerConsumption;

    private List<Corridor> mainCorridors = new ArrayList<>(4);
    private List<Corridor> subCorridors = new ArrayList<>(4);


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

    public static Floor createFloor(InPut inPut, int i) {
        final Floor floor = new Floor();
        floor.setFloorName("Floor " + (i + 1));
        floor.setPowerConsumption(floor.getPowerConsumption());

        final List<Corridor> mainCorridor = MainCorridor.createCorridor(inPut.getNoOfMainCorridors());

        final List<Corridor> subCorridor = SubCorridor.createCorridor(inPut.getNoOfSubCorridors());
        floor.setMainCorridors(mainCorridor);
        floor.setSubCorridors(subCorridor);

        return floor;
    }
}
