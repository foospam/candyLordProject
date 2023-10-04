package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class PayBackMoneyScreen extends AbstractScreen {
    public PayBackMoneyScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        switch(player.payBackDebt(Integer.parseInt(Controller.inputBuffer.get(0)))) {
            case DEBT_CANCELLED -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.PAY_BACK_IN_FULL));
            }
            case PARTIAL_PAYBACK_OK -> {
                Controller.inputBuffer.clear();
                Controller.inputBuffer.add(String.valueOf(player.getDebtValue()));
                setNextScreen(ScreenFactory.ofName(ScreenName.PAY_BACK_PARTIALLY));
            }
            case INSUFFICIENT_MONEY -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.PAY_BACK_TOO_MUCH));
            }
            case QUANTITY_NOT_WORTH_THE_FUSS -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.PAY_BACK_ZERO));
            }
        }
    }
}
