package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.auxiliaries.GamePersistor;
import com.sorianotapia.player.Player;

import java.io.IOException;

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
                try {
                    GamePersistor.loadGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
            }
        }
    }
}
