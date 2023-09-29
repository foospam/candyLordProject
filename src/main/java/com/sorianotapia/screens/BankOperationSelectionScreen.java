package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BankOperationSelectionScreen extends AbstractScreen {
    public BankOperationSelectionScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        switch (Controller.inputBuffer.get(0)) {
            case ("D") -> setNextScreen(ScreenFactory.ofName(ScreenName.DEPOSIT_MONEY));
            case ("W") -> setNextScreen(ScreenFactory.ofName(ScreenName.WITHDRAW_MONEY));
        }
    }


}
