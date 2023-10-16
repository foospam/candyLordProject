package com.sorianotapia.events;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.player.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

public class UpdatePricesDailyEvent extends PlaceEvent{

    public UpdatePricesDailyEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {
        PlaceContainer.randomUpdateStuffPrices();
    }

    @Override
    public boolean isLocalEvent() {
        return false;
    }

    @Override
    public void setPlace(Place place) {
    }

    @Override
    public void setPlayer(Player player) {

    }
}
