package com.sorianotapia.accessories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Holster {
    private HashMap<Arm, Integer> armMap = new HashMap<>();
    private ArrayList<Arm> armList = new ArrayList<>();
    private int armsInUse;
    private String stringRep;

    {
        add(ArmContainer.getArmByName("Beretta"));
    }

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

    public String printTopGuns(){
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Arm> arms = new ArrayList<>(armMap.keySet());
        Collections.reverse(arms);

        int upperBoundary = Math.min(arms.size(), 3);
        for (int i = 0; i < upperBoundary; i++) {

            stringBuilder.append(
                    String.format("%s (%s, %s %s %s %s) ",
                            arms.get(i).getName(),
                            armMap.get(arms.get(i)),
                            DisplaySymbols.HARM.toString(),
                            arms.get(i).getHarm(),
                            DisplaySymbols.ACCURACY.toString(),
                            arms.get(i).getAccuracy()));
            if (i < upperBoundary -1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    public ArrayList<Arm> getArmList(){
        return armList;
    }

    public String toString(){
        return stringRep;
    }

    public Holster(ArrayList<String> arms){
        for (String arm: arms){
            this.add(ArmContainer.getArmByName(arm));
        }
    }

    public Holster(){
    }

    public void clear() {
        armMap.clear();
        armList.clear();
    }
}
