package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;

public class SelectDrugQtyToSellScreen extends AbstractScreen {
    public SelectDrugQtyToSellScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        int stuffIndex = Integer.parseInt(Controller.inputBuffer.get(0)) - 1;
        String stuff = player.translateStuffIndexToName(stuffIndex);
        int stuffQty = Integer.parseInt(Controller.inputBuffer.get(1));

        switch (player.sellStuff(stuff, stuffQty)) {
            case SUCCESS:
                setNextScreen(ScreenFactory.ofName(ScreenName.EVENT_LOOP));
                break;
            case INSUFFICIENT_STASH: {
                setAdvanceDay(0);
                setNextScreen(ScreenFactory.ofName(ScreenName.STASH_EXCEEDED));
                break;
            }
            case QUANTITY_ZERO: {
                setAdvanceDay(0);
                setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
                break;
            }
        }
    }
}
