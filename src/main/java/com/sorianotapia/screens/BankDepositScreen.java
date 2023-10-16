package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;

public class BankDepositScreen extends AbstractScreen {
    public BankDepositScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        int quantity = Integer.parseInt(Controller.inputBuffer.get(0));

        switch(player.setDeposits(quantity)){
            case SUCCESS -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
            }
            case INSUFFICIENT_MONEY -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.DEPOSIT_EXCEEDED));
            }
        }
    }
}
