package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;

public class InputGrabberScreen extends AbstractScreen {

    public InputGrabberScreen(ScreenName name) {
        super(name);
    }

    @Override
    public String render(Player player) {
        return String.format(prompt, Controller.getDisplayInformationBuffer());
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(ScreenFactory.ofName(ScreenName.valueOf(defaultNextScreen)));
    }
}
