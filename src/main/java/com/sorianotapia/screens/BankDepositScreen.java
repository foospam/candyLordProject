package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BankDepositScreen extends AbstractScreen {
    public BankDepositScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        int quantity = Integer.parseInt(Controller.inputBuffer.get(0));

        switch(player.setDeposits(quantity)){
            case(0) : {
                setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
                break;
            }
            case(-1) : {
                setNextScreen(ScreenFactory.ofName(ScreenName.DEPOSIT_EXCEEDED));
                break;
            }
        }
    }
}
