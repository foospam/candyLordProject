package com.sorianotapia.accessories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ArmContainer {
    private static ArrayList<Arm> valueList = new ArrayList<>();
    private static HashMap<String, Arm> valueMap = new HashMap<>();
    private static ObjectMapper mapper = new ObjectMapper();
    private static Arm defaultArm;
    private static ObjectNode root;

    private static int armsInUse = 0;

    static {
        try {
            setFile("arms.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readAllStuffCarriers() {

        try {
            root.forEach(s -> {
                String name = s.get("name").asText();
                int harm = s.get("harm").asInt();
                int accuracy = s.get("accuracy").asInt();
                int price = s.get("price").asInt();
                Arm arm = new Arm(name, harm, accuracy, price);

                if (s.asText().equals("default")) {
                    defaultArm = arm;
                }
                else {
                    valueMap.put(name, arm);
                    valueList.add(arm);
                }
            });
        } catch (NullPointerException E) {
            throw new NullPointerException("The name file does not contain any arm elements.");
        }
    }

    public static Arm getRandomArm() {
        if (valueList.size() > 0) {
            int index = (int) (Math.random() * valueList.size());
            Arm arm = valueList.get(index);
            return arm;
        }
        return null;
    }

    public static Arm getArmByName(String name){
        return valueMap.get(name);
    }

    public static void setFile(String fileName) throws IOException {
        root = (ObjectNode) mapper.readTree(new File(fileName));
        readAllStuffCarriers();
    }

    public static Arm getDefaultArm(){
        return defaultArm;
    }



}
