package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;

import java.util.ArrayList;

public interface Fighter {

    public Object[] shootRandomEnemy(ArrayList<Fighter> enemies);
    public Object[] escapeEnemies(ArrayList<Fighter> enemies);
    public void setHarm(int harm);
    public int gunRoll();
    public int harmRoll();
    public void setArmInHand(Arm arm);

    String getName();
    public boolean isDead();
    public boolean isInBattle();

    public Arm giveArmInHand();

    public String combatInfoString();
}
