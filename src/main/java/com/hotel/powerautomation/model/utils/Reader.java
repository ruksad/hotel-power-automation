package com.hotel.powerautomation.model.utils;

import com.hotel.powerautomation.model.input.Input;

public interface Reader {

    Input read(String file);
    Input readInput();
}
