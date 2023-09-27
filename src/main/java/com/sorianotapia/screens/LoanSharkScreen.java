package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class LoanSharkScreen extends AbstractScreen {
    public LoanSharkScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        switch (stringArrayList.get(0)) {
            case "B" -> setNextScreen(screenFactory.ofName(ScreenName.BORROW_MONEY));
            case "P" -> setNextScreen(screenFactory.ofName(ScreenName.PAY_BACK_MONEY));
        }
    }
}
