package com.sorianotapia.accessories;

import com.sorianotapia.TextContainer;
import com.sorianotapia.combat.Fighter;

import java.util.concurrent.ThreadLocalRandom;

public class Arm implements Comparable {
    private String name;
    private int harm;
    private int accuracy;
    private int price;

    private boolean isDefault;

    public Arm(String name, int harm, int accuracy, int price, boolean isDefault) {
        this.name = name;
        this.harm = harm;
        this.accuracy = accuracy;
        this.price = price;
        this.isDefault = isDefault;
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

    public static String HIT;
    public static String MISS;


    static {
           HIT = TextContainer.getBattlePrompts("hit");
           MISS = TextContainer.getBattlePrompts("miss");
    }

    @Override
    public int compareTo(Object o) {
        Arm otherArm = (Arm) o;
        if (this.harm > otherArm.harm) return 1;
        else if (this.harm < otherArm.harm) return -1;
        else return 0;
    }

    public Object[] shoot(Fighter target, String shooterName){
        int die = ThreadLocalRandom.current().nextInt(100);
        if (die <= accuracy){
            target.setHarm(harm);
            return new Object[]{shooterName, target.getName(), harm};
        }
        return new Object[]{shooterName, target.getName(), -1};
    }

    public boolean isDefault() {
        return isDefault;
    }

    @Override
    public String toString() {
        return name + " (harm: "+harm+", acc.: "+accuracy+")";
    }
}
