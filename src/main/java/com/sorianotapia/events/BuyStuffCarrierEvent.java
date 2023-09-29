package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.accessories.StuffCarrier;
import com.sorianotapia.accessories.StuffCarrierContainer;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.ArrayList;

public class BuyStuffCarrierEvent extends Event {
    public BuyStuffCarrierEvent(Place place, Player player) {
        super(place, player);
    }

    @Override
    public void run(Controller controller, ScreenFactory screenFactory, ArrayList<String> buffer) {
        if (isLocalEvent()) {
            StuffCarrier stuffContainer = StuffCarrierContainer.getRandomCarrier();
            buffer.clear();
            buffer.add(stuffContainer.getName());
            buffer.add(String.valueOf(stuffContainer.getHold()));
            buffer.add(String.valueOf(stuffContainer.getPrice()));
            controller.setScreen(screenFactory.ofName(ScreenName.BUY_STUFF_CARRIER));
        }
    }
}
