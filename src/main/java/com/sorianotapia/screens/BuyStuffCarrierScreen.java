package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.goods.StuffCarrier;
import com.sorianotapia.goods.StuffCarrierContainer;
import com.sorianotapia.player.Player;

public class BuyStuffCarrierScreen extends AbstractScreen {
    public BuyStuffCarrierScreen(ScreenName name) {
        super(name);
    }

    public String render(Player player) {
        return String.format(prompt, Controller.getDisplayInformationBuffer());
    }

    @Override
    public void handleUserInput(Player player) {
        StuffCarrier carrier = StuffCarrierContainer.getCarrierByName(Controller.inputBuffer.get(0));
        if (Controller.inputBuffer.get(1).equals("N")) {
            setNextScreen(ScreenFactory.ofName(ScreenName.BUY_STUFF_CARRIER_NO));
        } else {
            switch (player.buyStuffCarrier(carrier)) {
                case INSUFFICIENT_MONEY -> setNextScreen(ScreenFactory.ofName(ScreenName.NO_MONEY));
                case SUCCESS -> setNextScreen(ScreenFactory.ofName(ScreenName.BUY_STUFF_CARRIER_OK));
            }
        }

    }
}
