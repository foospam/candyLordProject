package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class DrugBuyHoldExceededScreen extends ScreenAbstract{

    public DrugBuyHoldExceededScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
    }
}
