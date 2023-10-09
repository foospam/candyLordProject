package com.sorianotapia;

import com.sorianotapia.accessories.DisplaySymbols;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class GameInfo {

    public static GameInfo gameInfo;
    private ArrayList<String> stuffNames;
    private ArrayList<Integer> stuffPrices;
    private ArrayList<Integer> stuffQuantities;
    private String location;
    private int health;
    private int reputation;
    private int hold;
    private int maxHold;
    private int cash;
    private int deposits;
    private int debt;
    private int debtDays;
    private String date;
    private String gunType;

    public GameInfo(Player player, GameDate date){
        gameInfo = this;
        stuffNames = new ArrayList<>();
        stuffPrices = new ArrayList<>();
        stuffQuantities = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            stuffNames.add(player.getLocation().getStuffName(i));
        }

        updateGameInfo(player, date);
    }

    void updateGameInfo(Player player, GameDate date){

        stuffQuantities.clear();
        for (int i = 0; i < 8; i++) {
            stuffQuantities.add(player.getStuffOnHand(i));
        }

        stuffPrices.clear();
        for (int i = 0; i < 8; i++) {
            stuffPrices.add(player.getLocation().getStuffPrice(i));
        }

        location = player.getLocation().getName();
        this.date = date.getStringDate();
        health = player.getHealth();

        hold = player.getHold();
        maxHold = player.getMaxHold();
        cash = player.getCash();
        deposits = player.getDeposits();
        debt = player.getDebtValue();
        debtDays = player.getDebtDays();

    }

    public String printStuff(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            String stringLine = String.format("%s %-18s (you have %3s, price: %6s $)%n",
                    DisplaySymbols.STUFF_SYMBOL.toString(),
                    stuffNames.get(i),
                    stuffQuantities.get(i),
                    stuffPrices.get(i));
            stringBuilder.append(stringLine);
        }
        return stringBuilder.toString();
    }

    public String printUserStats(){
        StringBuilder stringBuilder = new StringBuilder();
        String placeTimeHealth = String.format("%s %s %s %s %s %s %s %s %s %s %s %s %s%n",
                DisplaySymbols.MAP.toString(),
                location,
                DisplaySymbols.TIME.toString(),
                date,
                DisplaySymbols.HEALTH.toString(),
                health,
                DisplaySymbols.CASH.toString(),
                cash,
                DisplaySymbols.BANK.toString(),
                deposits,
                DisplaySymbols.CREDIT.toString(),
                debt,
                (debt > 0) ? "("+debtDays+" days)" : ""
                );
        stringBuilder.append(placeTimeHealth);
        return stringBuilder.toString();
    }

    public ArrayList<String> getStuffNames(){
        return stuffNames;
    }
}
