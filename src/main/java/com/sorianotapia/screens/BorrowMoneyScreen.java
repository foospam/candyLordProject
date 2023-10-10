package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BorrowMoneyScreen extends AbstractScreen {
    public BorrowMoneyScreen(ScreenName name) {
        super(name);
    }


    @Override
    public void handleUserInput(Player player) {
        switch(player.borrowMoney(Integer.parseInt(Controller.inputBuffer.get(0)))){
            case SUCCESS -> {
                Controller.setDisplayInformationBuffer(new Object[]{player.getDebtDays()});
                setNextScreen(ScreenFactory.ofName(ScreenName.BORROW_MONEY_OK));}
            case MINIMUM_LOAN_NOT_REACHED -> setNextScreen(ScreenFactory.ofName(ScreenName.BORROW_MONEY_TOO_LITTLE));
            case MAXIMUM_CREDIT_EXCEEDED -> setNextScreen(ScreenFactory.ofName(ScreenName.BORROW_MONEY_TOO_MUCH));
            case CURRENT_CREDIT_EXCEEDED -> {
                Controller.setDisplayInformationBuffer(new Object[]{player.getDebtValue()});
                setNextScreen(ScreenFactory.ofName(ScreenName.BORROW_MONEY_NOT_OK));}

        }
    }


}
