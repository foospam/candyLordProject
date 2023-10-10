package com.sorianotapia.stuff;

import com.sorianotapia.TextContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class StuffContainer {
    HashMap<String, Integer> stuffMap;
    ArrayList<Stuff> stuffList;


    public StuffContainer(){
        stuffList = new ArrayList<>();
        stuffMap = new HashMap<>();
        fillStuffList();
    }

    private void fillStuffList(){
        ArrayList<String> stuffNames = TextContainer.getStuffNames();
        for (int i = 0; i < stuffNames.size(); i++) {
            String stuffName = stuffNames.get(i);
            Stuff newStuff = new Stuff(stuffName,
                    (int) Math.pow(3,stuffNames.size()+2-i),
                    (int) Math.pow(3,stuffNames.size()+1-i));
            stuffList.add(newStuff);
            stuffMap.put(stuffName, i);
        }
    }


    public Stuff getStuff(String name){
        return stuffList.get(stuffMap.get(name));
    }

    public int getPrice(String name){
        return stuffList.get(stuffMap.get(name)).getPrice();
    }

    public int getPrice(int index){
        return stuffList.get(index).getPrice();
    }

    public String getStuffName(int index){
        return stuffList.get(index).getName();
    }


    public Stuff getStuff(int index){
        return stuffList.get(index);
    }

    public int getSize(){
        return stuffList.size();
    }

    public void updateStuffPrices(int percentage) {
        for (Stuff stuff : stuffList){
            stuff.randomPriceUpdate(percentage);
        }
    }


}
