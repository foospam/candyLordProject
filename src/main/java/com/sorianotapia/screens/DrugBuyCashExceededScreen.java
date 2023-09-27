package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class DrugBuyCashExceededScreen extends ScreenAbstract{

    public DrugBuyCashExceededScreen(ScreenName name) {
        super(name);
        prompt = """
                You don't have that much money
                """;
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
    }
}
