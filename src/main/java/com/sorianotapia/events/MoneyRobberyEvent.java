package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.ArrayList;

public class MoneyRobberyEvent extends Event{
    public MoneyRobberyEvent(Place place, Player player) {
        super(place, player);
    }

    @Override
    public void run(Controller controller, ScreenFactory screenFactory, ArrayList<String> buffer) {
        if (isLocalEvent()){
            if (player.getCash() > 0 ) {
                player.emptyPockets();
                controller.setScreen(screenFactory.ofName(ScreenName.ROB_ALL_MONEY));
            } else {
                int health = player.getHealth();
                player.setHealth(health-5);
                controller.setScreen(screenFactory.ofName(ScreenName.ROB_ALL_MONEY_NO_OK));
            }
        }
    }
}
