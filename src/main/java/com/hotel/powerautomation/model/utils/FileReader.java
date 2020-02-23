package com.hotel.powerautomation.model.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.hotel.powerautomation.model.input.InPut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("fileReader")
public class FileReader implements Reader {

    @Override
    public InPut read(String file) {

        try (Stream<String> stringStream = Files.lines(Paths.get(file))) {
            final List<String> lines = stringStream.map(String::toLowerCase).collect(Collectors.toList());
            return InPut.readLine(lines);
        } catch (IOException e) {
            log.info("Error while reading {}", e);
        }
        return new InPut();
    }


}
