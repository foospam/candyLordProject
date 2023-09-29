package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class InputGrabberScreen extends AbstractScreen {

    public InputGrabberScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(ScreenFactory.ofName(ScreenName.valueOf(defaultNextScreen)));
    }
}
