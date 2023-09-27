package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BankOperationSelectionScreen extends ScreenAbstract {
    public BankOperationSelectionScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        switch (stringArrayList.get(0)) {
            case ("D") -> setNextScreen(screenFactory.ofName(ScreenName.DEPOSIT_MONEY));
            case ("W") -> setNextScreen(screenFactory.ofName(ScreenName.WITHDRAW_MONEY));
        }
    }


}
