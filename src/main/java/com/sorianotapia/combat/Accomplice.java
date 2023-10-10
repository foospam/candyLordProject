package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;
import com.sorianotapia.accessories.DisplaySymbols;

import java.util.ArrayList;

public class Accomplice extends NPC {

    final int bossReputation;

    public Accomplice(Arm arm, int bossReputation) {
        super(arm);
        this.bossReputation = bossReputation;
        icon = DisplaySymbols.GANGSTER.toString();
    }

    @Override
    public Object[] escapeEnemies(ArrayList<Fighter> enemies) {
        for (Fighter enemy : enemies) {
            int fighterRoll = gunRoll();
            int enemyRoll = enemy.gunRoll();
            if (enemyRoll > fighterRoll) {
                return new Object[]{false, name};
                };
            }
        inBattle = false;
        return new Object[]{true, name};
    }

    @Override
    public void setArmInHand(Arm arm) {

    }

    @Override
    public Object[] act(ArrayList<Fighter> enemies) {
        double willignessToFight =
                        ((double) health / 100) *
                        ((double) bossReputation / (double) (bossReputation+1));

        if (willignessToFight < 0.25){
            return escapeEnemies(enemies);
        }
        else return shootRandomEnemy(enemies);
    }
}
