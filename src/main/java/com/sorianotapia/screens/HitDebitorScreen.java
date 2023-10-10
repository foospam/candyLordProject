package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.GameSettings;
import com.sorianotapia.fromVersion1.Player;

public class HitDebitorScreen extends AbstractScreen{
    public HitDebitorScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        player.setHarm(GameSettings.DEBITOR_HARM);
        int overdue = Integer.parseInt(Controller.inputBuffer.get(0))-1;

        if (overdue > 0) {
            Controller.setDisplayInformationBuffer(new Object[]{overdue});
            setNextScreen(ScreenFactory.ofName(ScreenName.HIT_DEBITOR));
        }
        else {
            player.extendPaymentPeriod();
            setNextScreen(ScreenFactory.ofName(ScreenName.WARN_DEBITOR));
        }

    }
}
