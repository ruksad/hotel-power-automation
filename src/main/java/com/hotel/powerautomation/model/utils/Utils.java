package com.hotel.powerautomation.model.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import com.hotel.powerautomation.model.Floor;

public class Utils {

    private Utils() {
    }

    public static final Function<List<Integer>, Integer> addList = list -> {
        return list.stream().reduce(0, (a, b) -> a + b);
    };


    public static String getDigitsOfString(String line) {
        return line.replaceAll("[^0-9]", "");
    }

    public static ArrayList<?> customPrintableArrayList(List<?> floors) {
        List<?> floors1 = floors;
        ArrayList<Floor> arrayList = new ArrayList<Floor>() {
            @Override
            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                floors1.forEach(x -> stringBuilder.append(x.toString()));

                return stringBuilder.toString();

            }
        };
        return arrayList;
    }
}
