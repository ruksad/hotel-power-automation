package com.hotel.powerautomation.model.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.hotel.powerautomation.model.input.InPut;
import com.hotel.powerautomation.model.input.Move;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("fileReader")
public class FileReader implements Reader {

    @Override
    public InPut read(String file) {

        try (Stream<String> stringStream = Files.lines(Paths.get("file"))) {
            final List<String> lines = stringStream.map(String::toLowerCase).collect(Collectors.toList());
            return readLine(lines);
        } catch (IOException e) {
            log.info("Error while reading {}", e);
        }
        return new InPut();
    }

    private InPut readLine(List<String> lines) {
        final InPut inPut = new InPut();
        final ArrayList<Move> moves = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("number")) {
                String digits = getDigitsOfString(line);
                inPut.setNoOfFloors(Integer.parseInt(digits));
            } else if (line.startsWith("main")) {
                String digits = getDigitsOfString(line);
                inPut.setNoOfMainCorridors(Integer.parseInt(digits));
            } else if (line.startsWith("sub")) {
                String digits = getDigitsOfString(line);
                inPut.setNoOfSubCorridors(Integer.parseInt(digits));
            } else {
                moves.add(readMovement(line));
            }
        }
        inPut.setMoves(moves);
        return inPut;
    }

    private String getDigitsOfString(String line) {
        return line.replaceAll("[^0-9]", "");
    }

    private Move readMovement(String line) {
        Move move = new Move();
        if (line.startsWith("movement")) {
            move.setMovement(true);
            final String[] split = line.split(",");
            move.setFloorNumber(Integer.parseInt(getDigitsOfString(split[0])));
            move.setSubCorridorNumber(Integer.parseInt(getDigitsOfString(split[1])));
        } else if (line.startsWith("no movement")) {
            move.setMovement(false);
            final String[] split = line.split(",");
            move.setFloorNumber(Integer.parseInt(getDigitsOfString(split[0])));
            final String[] fors = split[1].split("for");
            move.setSubCorridorNumber(Integer.parseInt(getDigitsOfString(fors[0])));
            move.setNoMovementsForMinutes(Integer.parseInt(getDigitsOfString(fors[1])));
        }
        return move;
    }
}
