package com.hotel.powerautomation.model.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.hotel.powerautomation.model.input.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component("fileReader")
public class FileReader implements Reader {


    private String fileLocation;

    FileReader(@Value("${Hotel.power.autoMation-file}") String fileLocation) throws IOException {
        this.fileLocation = fileLocation;
        if (StringUtils.isEmpty(fileLocation)) {
            this.fileLocation = new ClassPathResource("input.txt").getURL().getFile();
        }
    }

    @Override
    public Input read(String file) {

        try (Stream<String> stringStream = Files.lines(Paths.get(file))) {
            final List<String> lines = stringStream.map(String::toLowerCase).collect(Collectors.toList());
            return Input.readLine(lines);
        } catch (IOException e) {
            log.info("Error while reading {}", e);
        }
        return new Input();
    }

    public Input readInput() {
        return this.read(this.fileLocation);
    }

}
