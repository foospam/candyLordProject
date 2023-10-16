package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

public class ReclaimDebtEvent extends UserEvent {
    public ReclaimDebtEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {
        controller.setScreen(ScreenFactory.ofName(ScreenName.RECLAIM_DEBT));
    }
}
