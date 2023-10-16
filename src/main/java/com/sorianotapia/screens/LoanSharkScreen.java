package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;

public class LoanSharkScreen extends AbstractScreen {
    public LoanSharkScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        switch (Controller.inputBuffer.get(0)) {
            case "B" -> setNextScreen(ScreenFactory.ofName(ScreenName.BORROW_MONEY));
            case "P" -> setNextScreen(ScreenFactory.ofName(ScreenName.PAY_BACK_MONEY));
        }
    }
}
