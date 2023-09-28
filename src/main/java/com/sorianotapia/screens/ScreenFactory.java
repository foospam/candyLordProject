package com.sorianotapia.screens;

import java.util.EnumMap;

public class ScreenFactory {

    private EnumMap<ScreenName, AbstractScreen> container;

    public ScreenFactory() {
        container = new EnumMap<ScreenName, AbstractScreen>(ScreenName.class);
    }

    public AbstractScreen ofName(ScreenName name) {
        if (container.containsKey(name)) return container.get(name);
        else {
            AbstractScreen screen = switch (name) {
                case MAIN_SELECTION -> new MainSelectionScreen(name);
                case SELECT_DRUG_TO_BUY, SELECT_DRUG_TO_SELL -> new InputGrabberScreen(name);
                case SELECT_QTY_TO_BUY -> new SelectDrugQtyToBuyScreen(name);
                case SELECT_QTY_TO_SELL -> new SelectDrugQtyToSellScreen(name);
                case BANK_OPERATION_SELECTION -> new BankOperationSelectionScreen(name);
                case DEPOSIT_MONEY -> new BankDepositScreen(name);
                case WITHDRAW_MONEY -> new BankWithdrawScreen(name);
                case GO_TO_HOSPITAL -> new GoToHospitalScreen(name);
                case LOAN_SHARK -> new LoanSharkScreen(name);
                case BORROW_MONEY -> new BorrowMoneyScreen(name);
                case TRAVEL -> new TravelScreen(name);
                case STASH_EXCEEDED, HOLD_EXCEEDED, CASH_EXCEEDED, DEPOSIT_EXCEEDED, WITHDRAW_LIMIT_EXCEEDED, HEALED,
                        NOT_HEALED, NO_CASH_FOR_HEALING, BORROW_MONEY_TOO_MUCH,
                        BORROW_MONEY_TOO_LITTLE, BORROW_MONEY_OK,
                        BORROW_MONEY_NOT_OK, PAY_BACK_IN_FULL, PAY_BACK_PARTIALLY,
                        PAY_BACK_ZERO, PAY_BACK_TOO_MUCH, TRAVEL_OK, TRAVEL_TOO_EXPENSIVE, TRAVEL_NOT_OK -> new OneLinerScreen(name);
                case PAY_BACK_MONEY -> new PayBackMoneyScreen(name);


                default -> null;
            };

            container.put(name, screen);
            return screen;
        }
    }
}

