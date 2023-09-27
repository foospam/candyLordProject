package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Drugs;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class SelectDrugQtyToSellScreen extends ScreenAbstract {
    public SelectDrugQtyToSellScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        int drugIndex = Integer.parseInt(stringArrayList.get(0)) - 1;
        Drugs drug = Drugs.values()[drugIndex];
        int drugQty = Integer.parseInt(stringArrayList.get(1));

//        if (drugQty > player.getDrugsOnHand(Drugs.values()[drugIndex])) {
//            setAdvanceDay(0);
//            setNextScreen(screenFactory.ofName(ScreenName.STASH_EXCEEDED));
//        } else if (drugQty == 0) {
//            setAdvanceDay(0);
//            setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
//        } else {
//            player.sellDrugs(Drugs.values()[drugIndex], drugQty);
//            setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
//        }

        switch (player.sellDrugs(Drugs.values()[drugIndex], drugQty)) {
            case 0:
                setNextScreen(screenFactory.ofName(ScreenName.MAIN_SELECTION));
                break;
            case -1: {
                setAdvanceDay(0);
                setNextScreen(screenFactory.ofName(ScreenName.CASH_EXCEEDED));
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
