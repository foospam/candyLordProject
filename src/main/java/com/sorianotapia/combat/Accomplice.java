package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;

import java.util.ArrayList;

public class Accomplice extends NPC {

    final int bossReputation;

    public Accomplice(Arm arm, int bossReputation) {
        super(arm);
        this.bossReputation = bossReputation;
    }

    @Override
    public String escapeEnemies(ArrayList<Fighter> enemies) {
        for (Fighter enemy : enemies) {
            int fighterRoll = gunRoll();
            int enemyRoll = enemy.gunRoll();
            if (enemyRoll > fighterRoll) {
                return name + " tried to escape, but the enemy was quicker!";
            }
        }
        inBattle = false;
        return name + " chickened out of the fight scene.";
    }

    @Override
    public void setArmInHand(Arm arm) {

    }

    @Override
    public String act(ArrayList<Fighter> enemies) {
        double willignessToFight =
                        ((double) health / 100) *
                        ((double) bossReputation / (double) (bossReputation+1));

        if (willignessToFight < 0.25){
            return escapeEnemies(enemies);
        }
        else return shootRandomEnemy(enemies);
    }
}
