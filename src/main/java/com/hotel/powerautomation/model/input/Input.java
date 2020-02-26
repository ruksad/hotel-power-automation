package com.hotel.powerautomation.model.input;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.model.utils.Utils;
import lombok.Data;

@Data
public class Input {
    private int noOfFloors;
    private int noOfMainCorridors;
    private int noOfSubCorridors;
    private List<Move> moves;


    public static Input readLine(List<String> lines) {
        final Input inPut = new Input();
        final ArrayList<Move> moves = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("number")) {
                String digits = Utils.getDigitsOfString(line);
                inPut.setNoOfFloors(Integer.parseInt(digits));
            } else if (line.startsWith("main")) {
                String digits = Utils.getDigitsOfString(line);
                inPut.setNoOfMainCorridors(Integer.parseInt(digits));
            } else if (line.startsWith("sub")) {
                String digits = Utils.getDigitsOfString(line);
                inPut.setNoOfSubCorridors(Integer.parseInt(digits));
            } else {
                moves.add(Move.readMovement(line));
            }
        }
        inPut.setMoves(moves);
        return inPut;
    }
}
