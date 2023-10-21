package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.controller.GameSettings;
import com.sorianotapia.player.Player;

public class RobMoneyScreen extends AbstractScreen {

    public RobMoneyScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        if (player.getCash() > 0) {
            int robberyPercent = player.emptyPockets();
            Controller.setDisplayInformationBuffer(new Object[]{robberyPercent});
            setNextScreen(ScreenFactory.ofName(ScreenName.ROB_ALL_MONEY));
        } else {
            int health = player.getHealth();
            player.setHealth(health - GameSettings.ROBBER_VENGEANCE_HARM);
            setNextScreen(ScreenFactory.ofName(ScreenName.ROB_ALL_MONEY_NO_OK));
        }
    }
}
