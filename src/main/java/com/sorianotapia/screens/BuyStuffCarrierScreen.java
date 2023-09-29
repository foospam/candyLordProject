package com.sorianotapia.screens;

import com.sorianotapia.accessories.StuffCarrier;
import com.sorianotapia.accessories.StuffCarrierContainer;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BuyStuffCarrierScreen extends AbstractScreen {
    public BuyStuffCarrierScreen(ScreenName name) {
        super(name);
    }

    public String render(ArrayList<String> inputBuffer, Player player) {
        String merchName = inputBuffer.get(0);
        String merchHold = inputBuffer.get(1);
        String merchPrice = inputBuffer.get(2);
        return String.format(prompt, merchName, merchHold, merchPrice);
    }

    @Override
    public void handleUserInput(ArrayList<String> inputBuffer, Player player, ScreenFactory screenFactory) {
        StuffCarrier carrier = StuffCarrierContainer.getCarrierByName(inputBuffer.get(0));
        if (inputBuffer.get(3) == "N") {
            setNextScreen(screenFactory.ofName(ScreenName.BUY_STUFF_CARRIER_NO));
        } else {
            switch (player.buyStuffCarrier(carrier)) {
                case INSUFFICIENT_MONEY -> setNextScreen(screenFactory.ofName(ScreenName.NO_MONEY));
                case SUCCESS -> setNextScreen(screenFactory.ofName(ScreenName.BUY_STUFF_CARRIER_OK));
            }
        }

    }
}
