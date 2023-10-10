package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.accessories.StuffCarrier;
import com.sorianotapia.accessories.StuffCarrierContainer;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.ArrayList;

public class BuyStuffCarrierEvent extends UserEvent {
    public BuyStuffCarrierEvent(Player player) {
        super(player);
    }

    @Override

    public void run(Controller controller) {
        if (isLocalEvent()) {

            StuffCarrier stuffContainer = StuffCarrierContainer.getRandomCarrier();

            String merchName = stuffContainer.getName();
            int merchHold = stuffContainer.getHold();
            int merchPrice = stuffContainer.getPrice();

            Controller.setDisplayInformationBuffer(
                    new Object[]{
                            merchName,
                            merchHold,
                            merchPrice
                    }
            );

            Controller.inputBuffer.clear();
            Controller.inputBuffer.add(stuffContainer.getName());
            controller.setScreen(ScreenFactory.ofName(ScreenName.BUY_STUFF_CARRIER));
        }
    }
}
