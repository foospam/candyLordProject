package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.player.Player;

public class BankWithdrawScreen extends AbstractScreen {
    public BankWithdrawScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        int quantity = Integer.parseInt(Controller.inputBuffer.get(0));

        switch(player.withdrawMoney(quantity)){
            case SUCCESS -> setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
            case INSUFFICIENT_MONEY -> setNextScreen(ScreenFactory.ofName(ScreenName.WITHDRAW_LIMIT_EXCEEDED));
        };
    }
}
