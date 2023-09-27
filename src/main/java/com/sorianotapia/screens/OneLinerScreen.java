package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class OneLinerScreen extends ScreenAbstract{

    public OneLinerScreen(ScreenName name){
        super(name);
    }

    @Override
    public String render(ArrayList<String> inputBuffer, Player player) {
        if (!inputBuffer.isEmpty()) return String.format(prompt, inputBuffer.get(0)); // Ojo esto solo vale para un valor
        return super.render(inputBuffer, player);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
    }
}
