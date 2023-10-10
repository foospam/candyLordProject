package com.sorianotapia.accessories;

import com.sorianotapia.TextContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class StuffCarrierContainer {
    private static ArrayList<StuffCarrier> valueList = new ArrayList<>();
    private static HashMap<String, StuffCarrier> valueMap = new HashMap<>();

    static {
        setValues();
    }

    private static void setValues() {
        valueMap.clear();
        valueList.clear();

        ArrayList<StuffCarrier> carriers = TextContainer.getAllStuffCarriers();
        carriers.forEach(s -> {
            String name = s.getName();
            valueMap.put(name, s);
            valueList.add(s);
        });
    }

    public static StuffCarrier getRandomCarrier() {
        if (valueList.size() > 0) {
            int index = (int) (Math.random() * valueList.size());
            StuffCarrier carrier = valueList.get(index);
            return carrier;
        }
        return null;
    }

    public static StuffCarrier getCarrierByName(String name) {
        return valueMap.get(name);
    }
}
