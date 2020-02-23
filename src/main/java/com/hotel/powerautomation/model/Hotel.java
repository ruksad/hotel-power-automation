package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.abstractmodels.Device;
import com.hotel.powerautomation.model.input.InPut;
import lombok.Data;

@Data
public class Hotel {

    private List<Floor> floors = new ArrayList<>();

    public static Hotel initializeHotel(final InPut inPut) {
        Hotel hotel = new Hotel();
        final ArrayList<Floor> floors = new ArrayList<>();
        for (int i = 0; i < inPut.getNoOfFloors(); i++) {
            floors.add(createFloor(inPut, i));
        }
        hotel.setFloors(floors);
        return hotel;
    }

    private static Floor createFloor(InPut inPut, int i) {
        final Floor floor = new Floor();
        floor.setFloorName("Floor " + (i + 1));
        floor.setPowerConsumption(floor.getPowerConsumption());

        final List<Corridor> mainCorridor = createMainCorridor(inPut.getNoOfMainCorridors());

        final List<Corridor> subCorridor = createSubCorridor(inPut.getNoOfSubCorridors());
        floor.setMainCorridors(mainCorridor);
        floor.setSubCorridors(subCorridor);

        return floor;
    }

    private static List<Corridor> createSubCorridor(int noOfCorridor) {
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

    private static List<Corridor> createMainCorridor(int noOfCorridor) {
        List<Corridor> subC = new ArrayList<>();
        for (int j = 0; j < noOfCorridor; j++) {
            final MainCorridor mainCorridor = new MainCorridor();
            mainCorridor.setMainCorridorName("Main corridor " + (j + 1));
            final List<Device> device = getDevice();
            mainCorridor.setDevices(device);
            subC.add(mainCorridor);
        }
        return subC;
    }

    private static List<Device> getDevice() {
        List<Device> device = new ArrayList<>(4);
        final Light light = new Light();
        final AirConditioner ac = new AirConditioner();
        device.add(light);
        device.add(ac);
        return device;

    }

}
