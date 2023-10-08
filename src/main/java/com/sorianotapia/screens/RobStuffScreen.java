package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

public class RobStuffScreen extends AbstractScreen {
    public RobStuffScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        if (player.getCash() > 0) {
            player.emptyHold();
            setNextScreen(ScreenFactory.ofName(ScreenName.ROB_ALL_STUFF));
        } else {
            int health = player.getHealth();
            player.setHealth(health - 5);
            setNextScreen(ScreenFactory.ofName(ScreenName.ROB_ALL_STUFF_NO_OK));
        }
    }
}