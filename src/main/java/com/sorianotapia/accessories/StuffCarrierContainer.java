package com.sorianotapia.accessories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StuffCarrierContainer {
    private static ArrayList<StuffCarrier> valueList = new ArrayList<>();
    private static HashMap<String, StuffCarrier> valueMap = new HashMap<>();
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectNode root;

    static {
        try {
            setFile("stuffCarriers.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readAllStuffCarriers() {

        try {
            root.forEach(s -> {
                String name = s.get("name").asText();
                int hold = s.get("hold").asInt();
                int price = s.get("price").asInt();
                StuffCarrier carrier = new StuffCarrier(name, hold, price);
                valueMap.put(name, carrier);
                valueList.add(carrier);
            });
        } catch (NullPointerException E) {
            throw new NullPointerException("The name file does not contain any carrier elements.");
        }
    }

    public static StuffCarrier getRandomCarrier() {
        if (valueList.size() > 0) {
            int index = (int) (Math.random() * valueList.size());
            StuffCarrier carrier = valueList.get(index);
            return carrier;
        }
        return null;
    }

    public static StuffCarrier getCarrierByName(String name){
        return valueMap.get(name);
    }

    public static void setFile(String fileName) throws IOException {
        root = (ObjectNode) mapper.readTree(new File(fileName));
        readAllStuffCarriers();
    }

}
