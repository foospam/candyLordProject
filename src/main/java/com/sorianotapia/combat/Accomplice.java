package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;

public class Accomplice extends NPC {

    final int bossReputation;

    public Accomplice(Arm arm, int bossReputation) {
        super(arm);
        this.bossReputation = bossReputation;
    }

    @Override
    public boolean escapeEnemies(Fighter... enemies) {
        for (Fighter enemy : enemies) {
            int fighterRoll = gunRoll();
            int enemyRoll = enemy.gunRoll();
            if (enemyRoll > fighterRoll) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void act(Fighter... enemies) {
        double willignessToFight =
                        ((double) health / 100) *
                        ((double) bossReputation / (double) (bossReputation+1));

        if (willignessToFight < 0.25){
            escapeEnemies(enemies);
        }
        else shootRandomEnemy(enemies);
    }
}
