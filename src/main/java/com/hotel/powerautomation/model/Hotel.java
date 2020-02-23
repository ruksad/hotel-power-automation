package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.model.input.InPut;
import com.hotel.powerautomation.model.input.Move;
import lombok.Data;

@Data
public class Hotel {

    private InPut inPut;
    private List<Floor> floors = new ArrayList<>();

    public static Hotel initializeHotel(final InPut inPut) {
        Hotel hotel = new Hotel();
        final ArrayList<Floor> floors = new ArrayList<>();
        for (int i = 0; i < inPut.getNoOfFloors(); i++) {
            floors.add(Floor.createFloor(inPut, i));
        }
        hotel.setFloors(floors);
        return hotel;
    }

    public void printSensorOutput(List<Move> moves){

    }

}
