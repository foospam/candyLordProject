package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.accessories.StuffCarrier;
import com.sorianotapia.accessories.StuffCarrierContainer;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BuyStuffCarrierScreen extends AbstractScreen {
    public BuyStuffCarrierScreen(ScreenName name) {
        super(name);
    }

    public String render(Player player) {
        String merchName = Controller.inputBuffer.get(0);
        String merchHold = Controller.inputBuffer.get(1);
        String merchPrice = Controller.inputBuffer.get(2);
        return String.format(prompt, merchName, merchHold, merchPrice);
    }

    @Override
    public void handleUserInput(Player player) {
        StuffCarrier carrier = StuffCarrierContainer.getCarrierByName(Controller.inputBuffer.get(0));
        if (Controller.inputBuffer.get(3).equals("N")) {
            setNextScreen(ScreenFactory.ofName(ScreenName.BUY_STUFF_CARRIER_NO));
        } else {
            switch (player.buyStuffCarrier(carrier)) {
                case INSUFFICIENT_MONEY -> setNextScreen(ScreenFactory.ofName(ScreenName.NO_MONEY));
                case SUCCESS -> setNextScreen(ScreenFactory.ofName(ScreenName.BUY_STUFF_CARRIER_OK));
            }
        }

    }
}
