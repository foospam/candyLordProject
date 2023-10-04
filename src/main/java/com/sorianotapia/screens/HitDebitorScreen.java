package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

public class HitDebitorScreen extends AbstractScreen{
    public HitDebitorScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        player.setHarm(25); // TO DO: Parametrizar esto
        int overdue = Integer.parseInt(Controller.inputBuffer.get(0));
        if (overdue > 0) {
            Controller.inputBuffer.clear();
            Controller.inputBuffer.add(String.valueOf(--overdue));
            setNextScreen(ScreenFactory.ofName(ScreenName.HIT_DEBITOR));
        }
        else {

            setNextScreen(ScreenFactory.ofName(ScreenName.WARN_DEBITOR));
            setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
        }

    }
}
