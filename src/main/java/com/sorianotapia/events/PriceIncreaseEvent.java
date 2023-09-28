package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;
import com.sorianotapia.stuff.Stuff;

import java.util.ArrayList;

public class PriceIncreaseEvent extends Event{


    public PriceIncreaseEvent(Place place, Player player) {
        super(place, player);
    }

    @Override
    public void run(Controller controller, ScreenFactory screenFactory, ArrayList<String> buffer) {

        Stuff stuff = place.getRandomStuff();
        stuff.priceUp(30);

        if (isLocalEvent()) {
            buffer.clear();
            buffer.add(stuff.getName());
            controller.setScreen(screenFactory.ofName(ScreenName.EVENT_PRICE_INCREASE));
        }
    }
}
