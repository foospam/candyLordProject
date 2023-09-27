package com.sorianotapia.fromVersion1;

import java.util.ArrayList;

class Rollo {

}

public enum City {

    CHICAGO, DETROIT, LAS_VEGAS, LOS_ANGELES, MIAMI, NEW_YORK, SAN_DIEGO, WASHINGTON_DC;
    ArrayList<Drugs> drugList;

    public static void main(String[] args) {
        System.out.println(City.WASHINGTON_DC.printableName());
        System.out.println(City.WASHINGTON_DC.getAllDrugPrices());
        System.out.println(City.WASHINGTON_DC.getAllDrugPrices());
        WASHINGTON_DC.updateDrugPrices();
        System.out.println(City.WASHINGTON_DC.getAllDrugPrices());
        System.out.println(City.NEW_YORK.getAllDrugPrices());

    }

    City() {

        drugList = new ArrayList<>();
        for (int i = 0; i < Drugs.values().length; i++) {
            drugList.add(Drugs.values()[i]);

        }
    }

    public void updateDrugPrices() {
        for (Drugs drug : drugList) {
            drug.updatePrice();
        }
    }

    public void increaseDrugPrices() {
        for (Drugs drug : drugList) {
            drug.priceUp();
        }
    }

    public void decreaseDrugPrices() {
        for (Drugs drug : drugList) {
            drug.priceDown();
        }
    }


    public ArrayList<Integer> getAllDrugPrices() {
        ArrayList<Integer> drugPrices = new ArrayList<>();
        for (Drugs drug : drugList) drugPrices.add(drug.getPrice());
        return drugPrices;
    }

    public int getDrugPrice(Drugs drug) {
        return drugList.get(drug.ordinal()).getPrice();
    }

    public String printableName() {
        String basic_name = this.toString();
        basic_name = basic_name.replace('_', ' ');
        return basic_name;
    }

}

