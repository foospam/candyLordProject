package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

public class GameOverEvent extends UserEvent {
    public GameOverEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {

    }
}
