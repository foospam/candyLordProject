package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Drugs;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class InputGrabberScreen extends ScreenAbstract{

    public InputGrabberScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        setNextScreen(screenFactory.ofName(ScreenName.valueOf(defaultNextScreen)));
    }
}
