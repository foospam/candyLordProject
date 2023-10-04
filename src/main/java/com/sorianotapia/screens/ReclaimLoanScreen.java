package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

public class ReclaimLoanScreen extends AbstractScreen{

    public ReclaimLoanScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        Controller.inputBuffer.clear();
        Controller.inputBuffer.add(String.valueOf(player.getOverdue()));
        setNextScreen(ScreenFactory.ofName(ScreenName.HIT_DEBITOR));
    }
}
