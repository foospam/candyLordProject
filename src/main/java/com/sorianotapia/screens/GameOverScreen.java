package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;

public class GameOverScreen extends AbstractScreen {
    public GameOverScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        switch (Controller.inputBuffer.get(0)) {
            case "N" -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.GOOD_BYE));
            }
            case "Y" -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.NEW_GAME));
            }
        }
    }
}
