package com.sorianotapia.fromVersion1;

import com.sorianotapia.Controller;
import com.sorianotapia.MethodAnswers;
import com.sorianotapia.TimeListener;
import com.sorianotapia.events.EventFactory;
import com.sorianotapia.events.EventMessage;
import com.sorianotapia.events.ReclaimDebtEvent;

public class LoanSharkDebt implements TimeListener {
    public static void main(String[] args) {

    }

    private boolean activeCredit;
    private int value;
    private final int INTEREST;
    private final int BASIC_LOAN;
    private final int MIN_LOAN;
    private final int MAX_PAYMENT_PERIOD;
    private final int MIN_PAYMENT_PERIOD;
    private int overdue;

    int paymentPeriod;

    public void raiseDebt() {
        value = (int) (value * (1 + INTEREST / 100.));
    }

    public void payOffDebt(int amount) {
        if (amount > value)
            value = 0;
    }

    public LoanSharkDebt() {
        value = 0;
        paymentPeriod = 0;
        this.BASIC_LOAN = 10000;
        this.MIN_LOAN = 1000;
        MAX_PAYMENT_PERIOD = 15;
        MIN_PAYMENT_PERIOD = 3;
        INTEREST = 10;
    }

    public int getValue() {
        return value;
    }

    public int getPaymentPeriod() {
        return paymentPeriod;
    }

    public MethodAnswers payBack(Player player, int quantity) {
        if (quantity > player.getCash()) return MethodAnswers.INSUFFICIENT_MONEY;
        else if (quantity < value / 10) return MethodAnswers.QUANTITY_NOT_WORTH_THE_FUSS;
        else {
            value -= quantity;
            player.setCash(player.getCash() - quantity);
            if (value == 0) {
                cancelDebt();
                return MethodAnswers.DEBT_CANCELLED;
            } else return MethodAnswers.PARTIAL_PAYBACK_OK;
        }
    }

    public MethodAnswers borrow(Player player, int quantity) {

        if (quantity < MIN_LOAN) return MethodAnswers.MINIMUM_LOAN_NOT_REACHED;
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

    private int getMaxCredit(Player player) {
        return player.getReputation() * BASIC_LOAN - 1;
    }

    private int getInitialPaymentPeriod(Player player, int quantity) {
        int maxCredit = getMaxCredit(player);
        int variablePaymentPeriod = MAX_PAYMENT_PERIOD - MIN_PAYMENT_PERIOD;
        double normalizedQuantity = (maxCredit - quantity) / (double) maxCredit;
        double factor = 1 / Math.pow(variablePaymentPeriod, normalizedQuantity); // Esto y la línea de abajo se puede simplificar a variablePaymentPeriod^normalized quantity
        int initialPaymentPeriod = (int) (variablePaymentPeriod * (1 - factor)) + MIN_PAYMENT_PERIOD;

        if (paymentPeriod <= 0) return initialPaymentPeriod;
        else return Math.min(paymentPeriod, initialPaymentPeriod);

        /*
         * 9999 3 días.
         * 8000 4 días.
         * 4999 6 días.
         * 4499 7 días.
         * 3500 8 días.
         * 2499 10 días.
         * 2000, 1999 11 días.
         * 1750 12 días
         * 1500 13 días
         * 1000 14 días
         */
    }

    public void updatePaymentPeriod(int days) {

        paymentPeriod -= days;
        if (paymentPeriod == -1) {
            overdue += 1;
            EventFactory.pushDebtEvent();
        }
    }

    private void cancelDebt() {
        activeCredit = false;
        paymentPeriod = 0;
    }


    @Override
    public void updateTime(int days) {
        if (activeCredit) {
            for (int i = 0; i < days; i++) {
                raiseDebt();
                updatePaymentPeriod(days);
            }
        }
    }

    @Override
    public void updateEvents(Object... args) {

    }

    public int getOverdue() {
        return overdue;
    }

    public void extendPaymentPeriod(){
        paymentPeriod = 10;
    }
}
