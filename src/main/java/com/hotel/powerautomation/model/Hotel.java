package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.framework.MovePublisher;
import com.hotel.powerautomation.model.input.Input;
import com.hotel.powerautomation.model.input.Move;
import lombok.Data;

@Data
public class Hotel {

    private Input inPut;
    private List<Floor> floors = new ArrayList<>();


    public boolean consumeMoves(final Input inPut) {
        final Hotel hotel = initializeHotel(inPut);
        final List<Move> moves = inPut.getMoves();

        for (int i = 0; i < moves.size(); i++) {
            Move m = moves.get(i);
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

            if (floor.isPowerConsumptionExceeding()) {
                movePublisher.notifyPowerConsumptionIncreasing(subCorridor, true);
            }
            if (floor.isPowerConsumptionUnderUnits() && !m.isMovement() && m.getNoMovementsForMinutes() > 1) {
                movePublisher.notifyPowerConsumptionIncreasing(subCorridor, false);
            }
            printHotelFloorState(hotel);
        }

        return true;
    }


    private static Hotel initializeHotel(final Input inPut) {
        Hotel hotel = new Hotel();
        hotel.setInPut(inPut);
        final ArrayList<Floor> floors = new ArrayList<>();
        for (int i = 0; i < inPut.getNoOfFloors(); i++) {
            floors.add(Floor.createFloor(inPut, i));
        }
        hotel.setFloors(floors);
        return hotel;
    }

    private void printHotelFloorState(Hotel hotel) {
        System.out.println(hotel.getFloors().toString().replaceAll("[\\[,\\]]", "").replaceAll("floorr[0-9]+", ""));
        System.out.println("---------------------------------------\n");
    }
}

