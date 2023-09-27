package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BankDepositScreen extends AbstractScreen {
    public BankDepositScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        int quantity = Integer.parseInt(stringArrayList.get(0));

        switch(player.setDeposits(quantity)){
            case(0) : {
                setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
                break;
            }
            case(-1) : {
                setNextScreen(screenFactory.ofName(ScreenName.DEPOSIT_EXCEEDED));
                break;
            }
        }
    }
}
