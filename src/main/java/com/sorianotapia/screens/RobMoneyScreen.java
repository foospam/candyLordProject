package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

public class RobMoneyScreen extends AbstractScreen {

    public RobMoneyScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        if (player.getCash() > 0) {
            player.emptyPockets();
            setNextScreen(ScreenFactory.ofName(ScreenName.ROB_ALL_MONEY));
        } else {
            int health = player.getHealth();
            player.setHealth(health - 5);
            setNextScreen(ScreenFactory.ofName(ScreenName.ROB_ALL_MONEY_NO_OK));
        }
    }
}
