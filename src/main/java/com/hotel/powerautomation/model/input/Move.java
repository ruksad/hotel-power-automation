package com.hotel.powerautomation.model.input;

import lombok.Data;

@Data
public class Move {

    private int floorNumber;
    private int subCorridorNumber;
    private boolean isMovement;
    private int noMovementsForMinutes;
}
