package com.sorianotapia.accessories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Holster {
    private HashMap<Arm, Integer> armMap;
    private ArrayList<Arm> armList;
    private int armsInUse;
    private String stringRep;


    public ArrayList<Arm> getTopGuns(int quantity){
        if (quantity <= armList.size()){
            return (ArrayList<Arm>) armList.subList(0,quantity);
        }
        else {
            ArrayList<Arm> topGuns = new ArrayList<Arm>(armList);
            for (int i = 0; i < quantity - armList.size(); i++) {
                topGuns.add(ArmContainer.getDefaultArm());
            }
            return topGuns;
        }
    }

    public Arm getTopGun(){
        if (armList.isEmpty()) {
            return ArmContainer.getDefaultArm();
        }
        else {
            Arm arm = armList.get(0);
            armList.remove(0);
            armMap.put(arm, armMap.get(arm)-1);
            return arm;
        }
    }

    public void add(Arm arm){
        armList.add(arm);
        if (!armMap.containsKey(arm)){
            armMap.put(arm, 1);
        }
        else {
            armMap.put(arm, armMap.get(arm)+1);
        }

        Collections.reverse(armList);
        printTopGuns();
    }

    private void printTopGuns(){
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Arm> arms = new ArrayList<>(armMap.keySet());
        Collections.reverse(arms);

        int upperBoundary = Math.min(arms.size(), 3);
        for (int i = 0; i < upperBoundary; i++) {
            stringBuilder.append(arms.get(i).getName()+": "+armMap.get(arms.get(i)));
            if (i < upperBoundary -1) {
                stringBuilder.append("\n");
            }
        }
    }

    public String toString(){
        return stringRep;
    }
}
