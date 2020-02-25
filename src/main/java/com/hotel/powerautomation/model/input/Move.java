package com.hotel.powerautomation.model.input;

import com.hotel.powerautomation.model.utils.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "noMovementsForMinutes")
public class Move {

    private int floorNumber;
    private int subCorridorNumber;
    private boolean isMovement;
    private int noMovementsForMinutes;
    private int moveDuplicateOfIndex;

    public static Move readMovement(String line) {
        Move move = new Move();
        if (line.startsWith("movement")) {
            move.setMovement(true);
            final String[] split = line.split(",");
            move.setFloorNumber(Integer.parseInt(Utils.getDigitsOfString(split[0])));
            move.setSubCorridorNumber(Integer.parseInt(Utils.getDigitsOfString(split[1])));
        } else if (line.startsWith("no movement")) {
            move.setMovement(false);
            final String[] split = line.split(",");
            move.setFloorNumber(Integer.parseInt(Utils.getDigitsOfString(split[0])));
            final String[] fors = split[1].split("for");
            move.setSubCorridorNumber(Integer.parseInt(Utils.getDigitsOfString(fors[0])));
            move.setNoMovementsForMinutes(Integer.parseInt(Utils.getDigitsOfString(fors[1])));
        }
        return move;
    }

}
