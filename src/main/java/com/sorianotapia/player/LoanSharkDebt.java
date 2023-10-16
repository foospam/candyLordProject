package com.sorianotapia.player;

import com.sorianotapia.GameSettings;
import com.sorianotapia.MethodAnswers;
import com.sorianotapia.TimeListener;
import com.sorianotapia.events.EventFactory;

public class LoanSharkDebt implements TimeListener {

    private boolean activeCredit;
    private int value;
    private int overdue;
    int paymentPeriod;

    public LoanSharkDebt() {
        value = 0;
        paymentPeriod = 0;
    }

    public LoanSharkDebt(int value, int paymentPeriod, boolean activeCredit, int overdue){
        this();
        this.value = value;
        this.paymentPeriod = paymentPeriod;
        this.overdue = overdue;
        this.activeCredit = activeCredit;
    }

    public MethodAnswers borrow(Player player, int quantity) {

        if (quantity < GameSettings.MIN_LOAN) return MethodAnswers.MINIMUM_LOAN_NOT_REACHED;
        else if (quantity > getMaxCredit(player)) return MethodAnswers.MAXIMUM_CREDIT_EXCEEDED;
        else if (quantity > (getMaxCredit(player) - value)) return MethodAnswers.CURRENT_CREDIT_EXCEEDED;
        else {
            value += quantity;
            player.setCash(player.getCash() + quantity);
            paymentPeriod = getInitialPaymentPeriod(player, quantity);
            activeCredit = true;
            return MethodAnswers.SUCCESS;
        }
    }

    public MethodAnswers payBack(Player player, int quantity) {
        if (quantity > player.getCash()) return MethodAnswers.INSUFFICIENT_MONEY;
        else if (quantity < value / 10) return MethodAnswers.QUANTITY_NOT_WORTH_THE_FUSS;
        else {

            if (quantity > value) {
                player.setCash(player.getCash() - value);
                value = 0;
            }
            else {
                value -= quantity;
                player.setCash(player.getCash() - quantity);
            }

            if (value == 0) {
                cancelDebt();
                return MethodAnswers.DEBT_CANCELLED;
            } else return MethodAnswers.PARTIAL_PAYBACK_OK;
        }
    }

    public void updatePaymentPeriod(int days) {

        paymentPeriod -= days;
        if (paymentPeriod == -1) {
            overdue += 1;
            EventFactory.pushDebtEvent();
        }
    }

    public void raiseDebt() {
        value = (int) (value * (1 + GameSettings.INTEREST_RATE / 100.));
    }

    private int getMaxCredit(Player player) {
        return player.getReputation() * GameSettings.BASIC_LOAN - 1;
    }

    private int getInitialPaymentPeriod(Player player, int quantity) {
        int maxCredit = getMaxCredit(player);
        int variablePaymentPeriod = GameSettings.MAX_PAYMENT_PERIOD - GameSettings.MIN_PAYMENT_PERIOD;
        double normalizedQuantity = (maxCredit - quantity) / (double) maxCredit;
        double factor = 1 / Math.pow(variablePaymentPeriod, normalizedQuantity); // Esto y la línea de abajo se puede simplificar a variablePaymentPeriod^normalized quantity
        int initialPaymentPeriod = (int) (variablePaymentPeriod * (1 - factor)) + GameSettings.MIN_PAYMENT_PERIOD;

        if (paymentPeriod <= 0) return initialPaymentPeriod;
        else return Math.min(paymentPeriod, initialPaymentPeriod);
    }

    private void cancelDebt() {
        activeCredit = false;
        paymentPeriod = 0;
    }


    public void extendPaymentPeriod(){
        paymentPeriod = 10;
    }

    @Override
    public void updateTime(int days) {
        if (activeCredit) {
            for (int i = 0; i < days; i++) {
                raiseDebt();
                updatePaymentPeriod(1);
            }
        }
    }

    public int getOverdue() {
        return overdue;
    }

    public int getPaymentPeriod() {
        return paymentPeriod;
    }

    public boolean getActiveCredit() {
        return activeCredit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void updateEvents(Object... args) {
    }
}
