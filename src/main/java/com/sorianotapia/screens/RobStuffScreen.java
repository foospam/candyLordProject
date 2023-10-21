package com.sorianotapia.screens;

import com.sorianotapia.auxiliaries.TextContainer;
import com.sorianotapia.controller.Controller;
import com.sorianotapia.controller.GameSettings;
import com.sorianotapia.player.Player;

public class RobStuffScreen extends AbstractScreen {
    public RobStuffScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        if (player.getHold() < player.getMaxHold()) {
            int robbedStuffUnits = player.emptyHold();
            System.out.println("Robbed stuff!!"+robbedStuffUnits);
            Controller.setDisplayInformationBuffer(
                    new Object[]{robbedStuffUnits,
                            robbedStuffUnits > 1 ? TextContainer.getGeneralTexts("pluralEnding") :
                    TextContainer.getGeneralTexts("singularEnding")});
            setNextScreen(ScreenFactory.ofName(ScreenName.ROB_ALL_STUFF));
        } else {
            int health = player.getHealth();
            player.setHealth(health - GameSettings.ROBBER_VENGEANCE_HARM);
            setNextScreen(ScreenFactory.ofName(ScreenName.ROB_ALL_STUFF_NO_OK));
        }
    }
}
