package com.hotel.powerautomation.model.utils;

import java.util.List;
import java.util.function.Function;

public class Utils {

    private Utils(){}

    public static Function<List<Integer>, Integer> addList = list -> {
        return list.stream().reduce(0, (a, b) -> a + b);
    };
}
