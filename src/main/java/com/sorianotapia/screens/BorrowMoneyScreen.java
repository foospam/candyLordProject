package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BorrowMoneyScreen extends ScreenAbstract {
    public BorrowMoneyScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        switch(player.borrowMoney(Integer.parseInt(stringArrayList.get(0)))){
            case 0 -> {
                stringArrayList.clear();
                stringArrayList.add(String.valueOf(player.getDebtDays()));
                setNextScreen(screenFactory.ofName(ScreenName.BORROW_MONEY_OK));}
            case -1 -> setNextScreen(screenFactory.ofName(ScreenName.BORROW_MONEY_TOO_LITTLE));
            case -2 -> setNextScreen(screenFactory.ofName(ScreenName.BORROW_MONEY_TOO_MUCH));
            case -3 -> {
                stringArrayList.clear();
                stringArrayList.add(String.valueOf(player.getDebtValue()));
                setNextScreen(screenFactory.ofName(ScreenName.BORROW_MONEY_NOT_OK));}

        }
    }


}
