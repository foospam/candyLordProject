package com.sorianotapia.goods;

import com.sorianotapia.auxiliaries.TextContainer;

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

        Collections.sort(armList);
        Collections.reverse(armList);
    }

    public String printTopGuns(){

        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Arm> differentArms = new ArrayList<>();
        for (Arm arm: armList) {
            if (!differentArms.contains(arm)) {
                differentArms.add(arm);
            }
            if (differentArms.size() >= 3) {
                break;
            }
        }

        for (int i = 0; i < differentArms.size(); i++) {

            stringBuilder.append(
                    String.format(TextContainer.getGeneralTexts("holsterArmString"),
                            DisplaySymbols.GUN.toString(),
                            differentArms.get(i).getName(),
                            armMap.get(differentArms.get(i)),
                            DisplaySymbols.HARM.toString(),
                            differentArms.get(i).getHarm(),
                            DisplaySymbols.ACCURACY.toString(),
                            differentArms.get(i).getAccuracy()));
            if (i < differentArms.size() -1) {
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
