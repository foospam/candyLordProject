package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

public class ReclaimLoanScreen extends AbstractScreen{

    public ReclaimLoanScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        Controller.setDisplayInformationBuffer(new Object[]{player.getOverdue()});
        setNextScreen(ScreenFactory.ofName(ScreenName.HIT_DEBITOR));
    }
}
