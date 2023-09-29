package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.ArrayList;

public class StuffRobberyEvent extends Event{
    public StuffRobberyEvent(Place place, Player player) {
        super(place, player);
    }

    @Override
    public void run(Controller controller, ScreenFactory screenFactory, ArrayList<String> buffer) {
        if (isLocalEvent()){
            if (player.getCash() > 0 ) {
                player.emptyHold();
                controller.setScreen(screenFactory.ofName(ScreenName.ROB_ALL_STUFF));
            } else {
                int health = player.getHealth();
                player.setHealth(health-5);
                controller.setScreen(screenFactory.ofName(ScreenName.ROB_ALL_STUFF_NO_OK));
            }
        }
    }
}
