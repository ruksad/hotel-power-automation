package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.hotel.powerautomation.framework.MovePublisher;
import com.hotel.powerautomation.model.input.InPut;
import com.hotel.powerautomation.model.input.Move;
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

        for (int i=0;i<moves.size();i++) {
            Move m=moves.get(i);
            String floorName = "Floor " + m.getFloorNumber();
            String subCorridorName = "floorr" + m.getFloorNumber() + "Sub corridor " + m.getSubCorridorNumber();

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

            if (!floor.isPowerConsumptionNeutral() && checkIfConsumableMove(m, moves,i)) {
                movePublisher.notifyPowerConsumptionIncreasing(subCorridor, floor.isPowerConsumptionExceeding());
            }
            printHotelFloorState(hotel);
        }

        return true;
    }

    private boolean checkIfConsumableMove(Move m, List<Move> moves,int i) {
        int index = moves.indexOf(m);
        int index1 = moves.lastIndexOf(m);
        return index1 - index == 1 && i==index;
    }

    private void printHotelFloorState(Hotel hotel) {
        System.out.println(hotel.getFloors().toString().replaceAll("[\\[,\\]]", "").replaceAll("floorr[0-9]+", ""));
        System.out.println("---------------------------------------\n");
    }
}

