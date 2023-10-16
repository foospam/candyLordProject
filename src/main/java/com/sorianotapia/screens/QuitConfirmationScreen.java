package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.auxiliaries.GamePersistor;
import com.sorianotapia.player.Player;

public class QuitConfirmationScreen extends AbstractScreen {
    public QuitConfirmationScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        switch (Controller.inputBuffer.get(0)){
            case "N" -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
            }
            case "Y" -> {
                try {
                    GamePersistor.saveGame(player);
                } catch (Exception e) {
                }
                setNextScreen(ScreenFactory.ofName(ScreenName.GOOD_BYE));
            }
        }
    }
}

