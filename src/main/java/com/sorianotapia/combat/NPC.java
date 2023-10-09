package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;
import com.sorianotapia.accessories.DisplaySymbols;
import com.sorianotapia.combat.Fighter;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class NPC implements Fighter {

    protected String icon;
    protected int health;
    protected String name;
    protected Arm armInHand;
    protected boolean inBattle;

    public NPC(Arm arm) {
        this.health = 100;
        this.armInHand = arm;
        this.inBattle = true;
    }

    @Override
    public String shootRandomEnemy(ArrayList<Fighter> enemies) {
        Fighter enemy = enemies.get(ThreadLocalRandom.current().nextInt(enemies.size()));
        return name + armInHand.shoot(enemy);
    }

    @Override
    public abstract String escapeEnemies(ArrayList<Fighter> enemies);

    @Override
    public void setHarm(int harm) {
        health = Math.max(health - harm, 0);
    }

    @Override
    public int gunRoll(){
        return ThreadLocalRandom.current().nextInt(armInHand.getAccuracy())
                + ThreadLocalRandom.current().nextInt(armInHand.getHarm());
    }

    public int harmRoll(){
        return ThreadLocalRandom.current().nextInt(armInHand.getHarm());
    }

    public abstract String act (ArrayList<Fighter> enemies);

    public String getName(){
        return this.name;
    }

    public boolean isDead(){
        return (health == 0);
    }

    public boolean isInBattle() {
        return inBattle;
    }

    public void setName(String name){
        this.name = name;
    }

    public Arm giveArmInHand(){
        Arm arm = armInHand;
        armInHand = null;
        return arm;
    }

    public String combatInfoString(){
        return icon +" "+name+": "+armInHand.toString()+", "+health+" health points";
    };
}
