package com.sorianotapia.screens;

import java.util.EnumMap;

public class ScreenFactory {

    static private EnumMap<ScreenName, AbstractScreen> container =
            new EnumMap<ScreenName, AbstractScreen>(ScreenName.class);

//    public ScreenFactory() {
//        container = new EnumMap<ScreenName, AbstractScreen>(ScreenName.class);
//    }

    public static AbstractScreen ofName(ScreenName name) {
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
                case BUY_STUFF_CARRIER -> new BuyStuffCarrierScreen(name);
                case BUY_ARM -> new BuyArmScreen(name);
                case RECLAIM_DEBT -> new ReclaimLoanScreen(name);
                case ROB_MONEY -> new RobMoneyScreen(name);
                case ROB_STUFF -> new RobStuffScreen(name);
                case EVENT_LOOP -> new EventLoopScreen(name);
                case HIT_DEBITOR -> new HitDebitorScreen(name);
                case GAME_OVER -> new GameOverScreen(name);
                case SET_COMBAT -> new SetCombatScreen(name);
                case COMBAT_ACTION_SELECTION -> new CombatActionSelection(name);
                case COMBAT_RESULT -> new CombatResultScreen(name);
                case QUIT_CONFIRMATION -> new QuitConfirmationScreen(name);
                case STASH_EXCEEDED, HOLD_EXCEEDED, CASH_EXCEEDED, DEPOSIT_EXCEEDED, WITHDRAW_LIMIT_EXCEEDED, HEALED,
                        NOT_HEALED, NO_CASH_FOR_HEALING, BORROW_MONEY_TOO_MUCH,
                        BORROW_MONEY_TOO_LITTLE,
                        BORROW_MONEY_NOT_OK, PAY_BACK_IN_FULL, PAY_BACK_PARTIALLY,
                        PAY_BACK_ZERO, PAY_BACK_TOO_MUCH, TRAVEL_TOO_EXPENSIVE, TRAVEL_NOT_OK, GOOD_BYE -> new OneLinerScreen(name);
                case PAY_BACK_MONEY -> new PayBackMoneyScreen(name);
                case BORROW_MONEY_OK, TRAVEL_OK, RELAX -> new EventLoopOneLinerScreen(name);
                case EVENT_PRICE_INCREASE, EVENT_PRICE_DECREASE,
                        BUY_STUFF_CARRIER_OK, BUY_STUFF_CARRIER_NO, NO_MONEY,
                        ROB_ALL_MONEY, ROB_ALL_MONEY_NO_OK,
                        ROB_ALL_STUFF, ROB_ALL_STUFF_NO_OK,
                        BUY_ARM_NO_OK, BUY_ARM_OK,
                        WARN_DEBITOR -> new EventLoopOneLinerScreen(name);
                default -> null;
            };

            container.put(name, screen);
            return screen;
        }
    }
}

