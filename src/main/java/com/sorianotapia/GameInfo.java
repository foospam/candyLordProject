package com.sorianotapia;

import com.sorianotapia.accessories.DisplaySymbols;
import com.sorianotapia.accessories.Holster;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.PlaceContainer;

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
    private Holster holster;

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
        reputation = player.getReputation();
        hold = player.getHold();
        maxHold = player.getMaxHold();
        cash = player.getCash();
        deposits = player.getDeposits();
        debt = player.getDebtValue();
        debtDays = player.getDebtDays();
        holster = player.getHolster();
    }

    public String printStuff(){
        StringBuilder stringBuilder = new StringBuilder();

        int halfTable = 4; // Parametrize this!
        for (int i = 0; i < halfTable; i++) {
            int j = i + halfTable;
            String row = String.format("%s %-18s (%s %3s %s %5s $)    %s %-18s (%s %3s %s %5s $)%n",
                    DisplaySymbols.STUFF_SYMBOL.toString(),
                    stuffNames.get(i),
                    DisplaySymbols.HAND.toString(),
                    stuffQuantities.get(i),
                    DisplaySymbols.MONEY.toString(),
                    stuffPrices.get(i),
                    DisplaySymbols.STUFF_SYMBOL.toString(),
                    stuffNames.get(j),
                    DisplaySymbols.HAND.toString(),
                    stuffQuantities.get(j),
                    DisplaySymbols.MONEY.toString(),
                    stuffPrices.get(j));
            stringBuilder.append(row);
        }

        return stringBuilder.toString();
    }

    public String printUserStats(){
        StringBuilder stringBuilder = new StringBuilder();
        String placeTimeHealth = String.format("%s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s (%s) ",
                DisplaySymbols.MAP.toString(),
                location,
                DisplaySymbols.TIME.toString(),
                date,
                DisplaySymbols.HEALTH.toString(),
                health,
                DisplaySymbols.REPUTATION.toString(),
                reputation,
                DisplaySymbols.CASH.toString(),
                cash,
                DisplaySymbols.BANK.toString(),
                deposits,
                DisplaySymbols.CREDIT.toString(),
                debt,
                (debt > 0) ? "("+debtDays+" days)" : "",
                DisplaySymbols.STASH.toString(),
                hold,
                maxHold
                );
        stringBuilder.append(placeTimeHealth);
        return stringBuilder.toString();
    }

    public String printUserArmory(){
        return DisplaySymbols.GUN.toString()+ " " + holster.printTopGuns();
    }


    public ArrayList<String> getStuffNames(){
        return stuffNames;
    }
}
