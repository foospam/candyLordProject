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
        int paybackAmount = Integer.parseInt(Controller.inputBuffer.get(0));
        switch(player.payBackDebt(paybackAmount)) {
            case DEBT_CANCELLED -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.PAY_BACK_IN_FULL));
            }
            case PARTIAL_PAYBACK_OK -> {
                Controller.setDisplayInformationBuffer(new Object[]{player.getDebtValue()});
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
