package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class InputGrabberScreen extends AbstractScreen {

    public InputGrabberScreen(ScreenName name) {
        super(name);
    }

    @Override
    public String render(Player player) {
        if (Controller.getDisplayInformationBuffer() != null && Controller.getDisplayInformationBuffer().length != 0) {
            return String.format(prompt, Controller.getDisplayInformationBuffer());
        }
        else return prompt;
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(ScreenFactory.ofName(ScreenName.valueOf(defaultNextScreen)));
    }
}
