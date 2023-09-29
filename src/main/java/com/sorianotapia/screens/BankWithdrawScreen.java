package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BankWithdrawScreen extends AbstractScreen {
    public BankWithdrawScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        int quantity = Integer.parseInt(Controller.inputBuffer.get(0));

        switch(player.withdrawMoney(quantity)){
            case(0) : setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
            case(-1) : setNextScreen(ScreenFactory.ofName(ScreenName.WITHDRAW_LIMIT_EXCEEDED));
        }
    }
}
