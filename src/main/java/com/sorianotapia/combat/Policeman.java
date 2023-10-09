package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;
import com.sorianotapia.accessories.DisplaySymbols;

import java.util.ArrayList;

public class Policeman extends NPC {
    public Policeman(Arm arm) {
        super(arm);
        icon = DisplaySymbols.POLICE_OFFICER.toString();
    }


    @Override
    public String escapeEnemies(ArrayList<Fighter> enemies) {
        inBattle = false;
        return name + " escaped the fight scene!";
    }

    @Override
    public void setArmInHand(Arm arm) {
    }
    @Override
    public String act(ArrayList<Fighter> enemies) {
        if (Math.random() < 0.8){
            return shootRandomEnemy(enemies);
        }
        else {
            return escapeEnemies(enemies);
        }
    }
}
