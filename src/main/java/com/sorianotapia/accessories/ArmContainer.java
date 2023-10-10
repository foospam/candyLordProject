package com.sorianotapia.accessories;

import com.sorianotapia.TextContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class ArmContainer {
    private static ArrayList<Arm> valueList = new ArrayList<>();
    private static HashMap<String, Arm> valueMap = new HashMap<>();
    private static Arm defaultArm;

    static {
        setValues();
    }

    private static void setValues() {

        valueList.clear();
        valueMap.clear();

        ArrayList<Arm> arms = TextContainer.readAllArms();
        arms.forEach(s -> {
            if (s.isDefault()) {
                defaultArm = s;
            }
            else {
                valueMap.put(s.getName(), s);
                valueList.add(s);
            }
        });
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

    public static Arm getDefaultArm(){
        return defaultArm;
    }
}
