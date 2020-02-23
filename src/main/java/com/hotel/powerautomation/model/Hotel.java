package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.hotel.powerautomation.framework.MovePublisher;
import com.hotel.powerautomation.framework.Observer;
import com.hotel.powerautomation.model.abstractmodels.Device;
import com.hotel.powerautomation.model.input.InPut;
import com.hotel.powerautomation.model.input.Move;
import lombok.Data;

@Data
public class Hotel {

    private InPut inPut;
    private List<Floor> floors = new ArrayList<>();

    private static Hotel initializeHotel(final InPut inPut) {
        Hotel hotel = new Hotel();
        final ArrayList<Floor> floors = new ArrayList<>();
        for (int i = 0; i < inPut.getNoOfFloors(); i++) {
            floors.add(Floor.createFloor(inPut, i));
        }
        hotel.setFloors(floors);
        return hotel;
    }

    public boolean consumeMoves(final InPut inPut) {
        final Hotel hotel = initializeHotel(inPut);
        final List<Move> moves = inPut.getMoves();
        final HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        moves.forEach(x->{
            String floorName = "Floor " + x.getFloorNumber();
            String subCorridorName = "Sub corridor " + x.getSubCorridorNumber();
            stringIntegerHashMap.compute(floorName+subCorridorName,(k,v)-> (v==null)?1:v+1);
        });
        for (Move m : moves) {

            String floorName = "Floor " + m.getFloorNumber();
            String subCorridorName = "Sub corridor " + m.getSubCorridorNumber();


            final Floor floor = Floor.findFloor(hotel.getFloors(), floorName);
            final SubCorridor subCorridor = SubCorridor.findSubCorridor(floor.getSubCorridors(), subCorridorName);

            final MovePublisher movePublisher = floor.getMovePublisher();

            if (m.isMovement()) {
                movePublisher.notifyUpdate(subCorridor, true);
            } else {
                if (m.getNoMovementsForMinutes() > 1 && stringIntegerHashMap.get(floorName+subCorridorName)>1) {
                    movePublisher.notifyUpdate(subCorridor, false);
                }
            }

            if (floor.isPowerConsumptionExceeding()) {
                for (Observer o : floor.getMovePublisher().getObservers()) {
                    final SubCorridor sc = (SubCorridor) o;
                    if (subCorridor.equals(sc)) {
                        continue;
                    }
                    for (Device device : sc.getDevices()) {
                        device.action(false);
                    }
                }
            }

        }
        return true;
    }


}

