package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;
import com.sorianotapia.stuff.Stuff;

import java.util.ArrayList;

public class PriceIncreaseEvent extends PlaceEvent{


    public PriceIncreaseEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {

        Stuff stuff = place.getRandomStuff();
        stuff.priceUp(30);

        if (isLocalEvent()) {
            Controller.setDisplayInformationBuffer(new Object[]{stuff.getName()});
            controller.setScreen(ScreenFactory.ofName(ScreenName.EVENT_PRICE_INCREASE));
        }
    }
}
