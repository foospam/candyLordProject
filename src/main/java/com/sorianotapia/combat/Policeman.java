package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;

import java.util.ArrayList;

public class Policeman extends NPC {
    public Policeman(Arm arm) {
        super(arm);
    }

    @Override
    public String escapeEnemies(ArrayList<Fighter> enemies) {
        return name + " escaped the fight scene!";
    }

    @Override
    public void setArmInHand(Arm arm) {
    }
    @Override
    public String act(ArrayList<Fighter> enemies) {
        return null;
    }
}
