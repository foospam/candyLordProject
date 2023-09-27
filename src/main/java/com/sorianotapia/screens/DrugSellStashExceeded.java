package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class DrugSellStashExceeded extends ScreenAbstract{

    public DrugSellStashExceeded(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
    }
}
