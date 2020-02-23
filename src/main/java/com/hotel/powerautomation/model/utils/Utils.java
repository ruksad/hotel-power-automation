package com.hotel.powerautomation.model.utils;

import java.util.List;
import java.util.function.Function;

public class Utils {

    private Utils(){}

    public static final Function<List<Integer>, Integer> addList = list -> {
        return list.stream().reduce(0, (a, b) -> a + b);
    };


    public static String getDigitsOfString(String line) {
        return line.replaceAll("[^0-9]", "");
    }
}
