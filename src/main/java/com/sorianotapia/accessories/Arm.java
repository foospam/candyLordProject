package com.sorianotapia.accessories;

import com.sorianotapia.combat.Fighter;

import java.util.concurrent.ThreadLocalRandom;

public class Arm implements Comparable {
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


    @Override
    public int compareTo(Object o) {
        Arm otherArm = (Arm) o;
        if (this.harm > otherArm.harm) return 1;
        else if (this.harm < otherArm.harm) return -1;
        else return 0;
    }

    public String shoot(Fighter fighter){
        int die = ThreadLocalRandom.current().nextInt(100);
        if (die <= accuracy){
            fighter.setHarm(harm);
            return " attacked "+fighter.getName()+". "+fighter.getName() +" took " + harm + " points of harm,";
        }
    }

}
