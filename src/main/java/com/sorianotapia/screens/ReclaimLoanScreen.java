package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.player.Player;

public class ReclaimLoanScreen extends AbstractScreen{

    public ReclaimLoanScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        Controller.inputBuffer.add(String.valueOf(player.getOverdue()));
        setNextScreen(ScreenFactory.ofName(ScreenName.HIT_DEBITOR));
    }
}
