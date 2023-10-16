package com.sorianotapia.events;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

public class GameOverEvent extends UserEvent {
    public GameOverEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {
        controller.setScreen(ScreenFactory.ofName(ScreenName.GAME_OVER));
    }
}
