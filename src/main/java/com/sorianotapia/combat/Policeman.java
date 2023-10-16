package com.sorianotapia.combat;

import com.sorianotapia.goods.Arm;
import com.sorianotapia.goods.DisplaySymbols;

import java.util.ArrayList;

public class Policeman extends NPC {
    public Policeman(Arm arm) {
        super(arm);
        icon = DisplaySymbols.POLICE_OFFICER.toString();
    }


    @Override
    public Object[] escapeEnemies(ArrayList<Fighter> enemies) {
        inBattle = false;
        return new Object[]{true, name};
    }

    @Override
    public void setArmInHand(Arm arm) {
    }
    @Override
    public Object[] act(ArrayList<Fighter> enemies) {
        if (Math.random() < 0.8){
            return shootRandomEnemy(enemies);
        }
        else {
            return escapeEnemies(enemies);
        }
    }
}
