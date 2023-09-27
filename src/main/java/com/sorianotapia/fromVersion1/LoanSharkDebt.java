package com.sorianotapia.fromVersion1;

import com.sorianotapia.TimeListener;

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

    int paymentPeriod;

    public void raiseDebt() {
        value = (int) (value * (1 + INTEREST / 100.));
        System.out.println("Value: " + value);
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

    public int payBack(Player player, int quantity) {
        if (quantity > player.getCash()) return -1;
        else if (quantity < value / 10) return -2;
        else {
            value -= quantity;
            player.setCash(player.getCash() - quantity);
            if (value == 0) {
                cancelDebt();
                return 1;
            } else return 0;
        }
    }

    public int borrow(Player player, int quantity) {

        if (quantity < MIN_LOAN) return -1;
        else if (quantity > getMaxCredit(player)) return -2;
        else if (quantity > (getMaxCredit(player) - value)) return -3;
        else {
            value += quantity;
            player.setCash(player.getCash() + quantity);
            paymentPeriod = getInitialPaymentPeriod(player, quantity);
            return 0;
        }
    }

    private int getMaxCredit(Player player) {
        return player.getReputation() * BASIC_LOAN - 1;
    }

    private int getInitialPaymentPeriod(Player player, int quantity) {
        int maxCredit = getMaxCredit(player) - MIN_LOAN;
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

    public void updatePaymentPeriod() {
        paymentPeriod--;
        if (paymentPeriod == -1) {

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
            }
        }
    }

    @Override
    public void updateEvents(Object... args) {

    }
}
