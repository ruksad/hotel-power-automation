package com.hotel.powerautomation.service;

import javax.annotation.PostConstruct;
import com.hotel.powerautomation.model.Hotel;
import com.hotel.powerautomation.model.input.Input;
import com.hotel.powerautomation.model.utils.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DriverService {

    Reader filReader;

    @Autowired
    DriverService(@Qualifier("fileReader") Reader reader){
        this.filReader=reader;
    }

    @PostConstruct
    public void driveHotel() {
        final Hotel hotel = new Hotel();
        final Input input = filReader.readInput();
        hotel.consumeMoves(input);
    }
}
