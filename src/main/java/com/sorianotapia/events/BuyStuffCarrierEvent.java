package com.sorianotapia.events;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.goods.StuffCarrier;
import com.sorianotapia.goods.StuffCarrierContainer;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

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
