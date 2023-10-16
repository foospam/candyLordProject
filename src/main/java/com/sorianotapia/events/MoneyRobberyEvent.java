package com.sorianotapia.events;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

public class MoneyRobberyEvent extends UserEvent{
    public MoneyRobberyEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {
        controller.setScreen(ScreenFactory.ofName(ScreenName.ROB_MONEY));
    }
}
