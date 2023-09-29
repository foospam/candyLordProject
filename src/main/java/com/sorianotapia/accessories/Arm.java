package com.sorianotapia.accessories;

public class Arm {
    private String name;
    private int harm;
    private int accuracy;
    private int price;

    public Arm(String name, int harm, int accuracy, int price) {
        this.name = name;
        this.harm = harm;
        this.accuracy = accuracy;
        this.price = price;
    }

    public int getHarm() {
        return harm;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


}
