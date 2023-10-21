package com.sorianotapia.controller;

public class GameSettings {
    public static final int DEBITOR_HARM = 25;
    public static final int RAMDOM_PRICE_VARIATION_PERCENTAGE = 3;
    public static final double RANDOM_PLACE_EVENT_FREQ = 0.2;
    public static final double RANDOM_USER_EVENT_FREQ = 0.3;
    public static final int WORLD_SIZE = 20;
    public static final int AROUND_WORLD_TICKET_PRICE = 500;
    public static final int PRICE_PER_KM = AROUND_WORLD_TICKET_PRICE / WORLD_SIZE;
    public static final int MIN_DISTANCE = 5;
    public static final int NUMBER_OF_PLACES = 8;
    public static final int INITIAL_HEALTH = 100;
    public static final int INITIAL_CASH = 500;
    public static final int INITIAL_HOLD = 10;
    public static final int INITIAL_REPUTATION = 1;

    // Loan shark settings

    public static final int BASIC_LOAN = 10000;
    public static final int MIN_LOAN = 1000;
    public static final int MAX_PAYMENT_PERIOD = 15;
    public static final int MIN_PAYMENT_PERIOD = 3;
    public static final int INTEREST_RATE = 10;
    public static final int ROBBER_VENGEANCE_HARM = 5;

    // Robbery settings
    public static final int MAX_CASH_ROBBERY_PERCENT = 80;
    public static final int MIN_CASH_ROBBERY_PERCENT = 20;

    public static final int MAX_STUFF_ROBBERY_PERCENT = 80;
    public static final int MIN_STUFF_ROBBERY_PERCENT = 20;
}
