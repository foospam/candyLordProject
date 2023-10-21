package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.player.Player;

public class SelectDrugQtyToBuyScreen extends AbstractScreen {
    public SelectDrugQtyToBuyScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        int stuffIndex = Integer.parseInt(Controller.inputBuffer.get(0)) - 1;
        String stuff = player.translateStuffIndexToName(stuffIndex);
        int stuffQty = Integer.parseInt(Controller.inputBuffer.get(1));

        switch (player.buyStuff(stuff, stuffQty)) {
            case SUCCESS -> setNextScreen(ScreenFactory.ofName(ScreenName.EVENT_LOOP));
            case INSUFFICIENT_MONEY -> {
                setAdvanceDay(0);
                setNextScreen(ScreenFactory.ofName(ScreenName.CASH_EXCEEDED));
            }
            case INSUFFICIENT_HOLD -> {
                setAdvanceDay(0);
                setNextScreen(ScreenFactory.ofName(ScreenName.HOLD_EXCEEDED));
            }
            case QUANTITY_ZERO -> {
                setAdvanceDay(0);
                setNextScreen(ScreenFactory.ofName(ScreenName.ZERO_STUFF_TO_BUY));
            }
        }
    }
}
