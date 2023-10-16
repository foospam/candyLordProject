package com.sorianotapia.events;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

public class StuffRobberyEvent extends UserEvent {
    public StuffRobberyEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {
        controller.setScreen(ScreenFactory.ofName(ScreenName.ROB_STUFF));
    }
}
