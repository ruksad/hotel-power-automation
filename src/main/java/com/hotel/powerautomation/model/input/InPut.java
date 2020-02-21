package com.hotel.powerautomation.model.input;

import java.util.List;
import lombok.Data;

@Data
public class InPut {
    private int noOfFloors;
    private int noOfMainCorridors;
    private int noOfSubCorridors;
    private List<Move> moves;
}
