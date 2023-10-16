package com.sorianotapia.events;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;
import com.sorianotapia.goods.Stuff;

public class PriceDecreaseEvent extends PlaceEvent{


    public PriceDecreaseEvent(Player player) {
        super(player);
    }


    @Override
    public void run(Controller controller) {

        Stuff stuff = place.getRandomStuff();
        stuff.priceDown(30);

        if (isLocalEvent()) {
            Controller.setDisplayInformationBuffer(new Object[]{stuff.getName()});
            controller.setScreen(ScreenFactory.ofName(ScreenName.EVENT_PRICE_DECREASE));
        }
    }

}
