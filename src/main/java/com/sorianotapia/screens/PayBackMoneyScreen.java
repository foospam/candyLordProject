package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class PayBackMoneyScreen extends AbstractScreen {
    public PayBackMoneyScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        switch(player.payBackDebt(Integer.parseInt(stringArrayList.get(0)))) {
            case 1 -> {
                setNextScreen(screenFactory.ofName(ScreenName.PAY_BACK_IN_FULL));
            }
            case 0 -> {
                stringArrayList.clear();
                stringArrayList.add(String.valueOf(player.getDebtValue()));
                setNextScreen(screenFactory.ofName(ScreenName.PAY_BACK_PARTIALLY));
            }
            case -1 -> {
                setNextScreen(screenFactory.ofName(ScreenName.PAY_BACK_TOO_MUCH));
            }
            case -2 -> {
                setNextScreen(screenFactory.ofName(ScreenName.PAY_BACK_ZERO));
            }
        }
    }
}
