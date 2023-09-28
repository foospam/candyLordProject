package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class SelectDrugQtyToSellScreen extends AbstractScreen {
    public SelectDrugQtyToSellScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        int stuffIndex = Integer.parseInt(stringArrayList.get(0)) - 1;
        String stuff = player.translateStuffIndexToName(stuffIndex);
        int stuffQty = Integer.parseInt(stringArrayList.get(1));

        switch (player.sellStuff(stuff, stuffQty)) {
            case 0:
                setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
                break;
            case -1: {
                setAdvanceDay(0);
                setNextScreen(screenFactory.ofName(ScreenName.STASH_EXCEEDED));
                break;
            }
            case -2: {
                setAdvanceDay(0);
                setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
                break;
            }
        }
    }
}
