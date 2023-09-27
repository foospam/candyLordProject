package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BankWithdrawScreen extends ScreenAbstract {
    public BankWithdrawScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        int quantity = Integer.parseInt(stringArrayList.get(0));

        switch(player.withdrawMoney(quantity)){
            case(0) : setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
            case(-1) : setNextScreen(screenFactory.ofName(ScreenName.WITHDRAW_LIMIT_EXCEEDED));
        }
    }
}
