package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.hotel.powerautomation.framework.MovePublisher;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.input.Input;
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
    private MovePublisher movePublisher;

    private List<Corridor> mainCorridors = new ArrayList<>(4);
    private List<Corridor> subCorridors = new ArrayList<>(4);


    public static int calculatePowerConsumption(int a, int b) {
        return powerConsumptionFormula.apply(a, b);
    }

    public int maxPowerConsumption() {
        int a = this.getMainCorridors().size();
        int b = this.getSubCorridors().size();
        return calculatePowerConsumption(a, b);
    }

    public int currentPowerConsumption() {
        final List<Integer> mainCorridorPowerConsumption = extractDevicePowerConsumption.apply(this.mainCorridors);
        final List<Integer> subCorridorPowerConsumption = extractDevicePowerConsumption.apply(this.subCorridors);
        Integer mpc = Utils.addList.apply(mainCorridorPowerConsumption);
        Integer scpc = Utils.addList.apply(subCorridorPowerConsumption);
        return mpc + scpc;
    }

    public static Floor createFloor(Input inPut, int floorNumber) {
        final Floor floor = new Floor();
        floor.setFloorName("Floor " + (floorNumber + 1));
        floor.setPowerConsumption(floor.getPowerConsumption());

        final List<Corridor> mainCorridor = MainCorridor.createCorridor("floorr"+(floorNumber+1),inPut.getNoOfMainCorridors());

        final List<Corridor> subCorridor = SubCorridor.createCorridor("floorr"+(floorNumber+1),inPut.getNoOfSubCorridors());

        final MovePublisher movePublisher = createObservers(subCorridor);

        floor.setMovePublisher(movePublisher);
        floor.setMainCorridors(mainCorridor);
        floor.setSubCorridors(subCorridor);

        return floor;
    }

    private static MovePublisher createObservers(List<Corridor> subCorridor) {
        final MovePublisher movePublisher = new MovePublisher();
        subCorridor.stream().map(x->(SubCorridor)x).forEach(x->movePublisher.attach(x));
        return movePublisher;
    }

    public static Floor findFloor(List<Floor> list,String floorName){
        return list.stream().filter(x -> x.getFloorName().equals(floorName)).findFirst().get();
    }
    public boolean isPowerConsumptionExceeding(){
        return currentPowerConsumption()>maxPowerConsumption();
    }

    public boolean isPowerConsumptionUnderUnits(){
        return currentPowerConsumption()<maxPowerConsumption();
    }
    public int powerConsumptionExceedingFactor(){
        return currentPowerConsumption()-maxPowerConsumption();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\t\tFloor ");
        sb.append(getFloorName()).append("\t\t\t\n").append(getMainCorridors().toString())
            .append("\n").append(getSubCorridors().toString()).append("\n");
        return sb.toString();
    }
}
