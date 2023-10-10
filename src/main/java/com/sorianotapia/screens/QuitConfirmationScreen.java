package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

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
                setNextScreen(ScreenFactory.ofName(ScreenName.GOOD_BYE));
            }
        }
    }
}
