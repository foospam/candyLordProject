package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;
import com.sorianotapia.combat.Fighter;

import java.util.concurrent.ThreadLocalRandom;

public abstract class NPC implements Fighter {

    protected int health;
    protected Arm armInHand;

    public NPC(Arm arm) {
        this.health = 100;
        this.armInHand = arm;
    }

    @Override
    public void shootRandomEnemy(Fighter... enemies) {
        Fighter enemy = enemies[ThreadLocalRandom.current().nextInt(enemies.length)];
        armInHand.shoot(enemy);
    }

    @Override
    public abstract boolean escapeEnemies(Fighter... enemies);

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

    public abstract void act(Fighter... enemies);

}
