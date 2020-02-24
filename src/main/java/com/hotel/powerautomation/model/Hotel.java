package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.framework.MovePublisher;
import com.hotel.powerautomation.framework.Observer;
import com.hotel.powerautomation.model.abstractmodels.Device;
import com.hotel.powerautomation.model.input.InPut;
import com.hotel.powerautomation.model.input.Move;
import com.hotel.powerautomation.model.utils.Utils;
import lombok.Data;

@Data
public class Hotel {

    private InPut inPut;
    private List<Floor> floors = new ArrayList<>();

    private static Hotel initializeHotel(final InPut inPut) {
        Hotel hotel = new Hotel();
        hotel.setInPut(inPut);
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

        for (Move m : moves) {

            String floorName = "Floor " + m.getFloorNumber();
            String subCorridorName = "Sub corridor " + m.getSubCorridorNumber();

            final Floor floor = Floor.findFloor(hotel.getFloors(), floorName);
            final SubCorridor subCorridor = SubCorridor.findSubCorridor(floor.getSubCorridors(), subCorridorName);

            final MovePublisher movePublisher = floor.getMovePublisher();

            if (m.isMovement()) {
                movePublisher.notifyUpdate(subCorridor, true);
            } else {
                if (m.getNoMovementsForMinutes() > 1) {
                    movePublisher.notifyUpdate(subCorridor, false);
                }
            }

            movePublisher.notifyPowerConsumptionIncreasing(subCorridor,floor.isPowerConsumptionExceeding());


            System.out.println(hotel.getFloors().toString().replaceAll("[\\[,\\]]", ""));
            System.out.println("---------------------------------------\n");
        }

        return true;
    }
}

