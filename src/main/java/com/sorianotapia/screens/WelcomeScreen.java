package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

public class WelcomeScreen extends AbstractScreen {

    public WelcomeScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        String input = Controller.inputBuffer.get(0);
        switch (input) {
            case "1" -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
            }
            case "2" -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.GOOD_BYE));
            }
        }
    }
}
