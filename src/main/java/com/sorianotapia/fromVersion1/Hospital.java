package com.sorianotapia.fromVersion1;

import java.util.ArrayList;

public class Hospital {

    public int heal(Player player){
        int playerCash = player.getCash();
        int healingCost = getHealingCost(player);

        if (playerCash < healingCost) return -1;
        else {
            player.setHealth(100);
            player.setCash(playerCash-healingCost);
            return 0;
        }
    }

    public int getHealingTime(Player player){
        int lifePointDiff = 100 - player.getHealth();
        return lifePointDiff / 10;
    }

    public int getHealingCost(Player player){
        int lifePointDiff = 100 - player.getHealth();
        return (int) Math.pow(2, (double) (2 + lifePointDiff / 10));
    }

}
