package com.sorianotapia.fromVersion1;

import com.sorianotapia.places.NameContainer;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

import java.util.EnumMap;
import java.util.HashMap;

public class Player {
    private int maxHold;
    private int hold;
    private int health;
    private Hospital hospital;
    private int reputation;
    private int cash;
    private LoanSharkDebt debt;
    private BankAccount bankAccount;
    private Place location;
    private HashMap<String, Integer> stuffOnHand;

    private int numberOfGuns;

    private String gunType;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public Player() {
        hospital = new Hospital();
        health = 100;
        reputation = 1;
        cash = 500;
        maxHold = 10;
        hold = 10;

        bankAccount = new BankAccount(this);
        location = PlaceContainer.getRandomPlace();
        stuffOnHand = new HashMap<>();
        for (String name: NameContainer.getStuffNames()) stuffOnHand.put(name, 0);

    }


    public int withdrawMoney(int amount) {
        return bankAccount.withdraw(amount);
    }

    public int getDeposits() {
        return bankAccount.getDeposits();
    }

    public int setDeposits(int amount) {
        return bankAccount.deposit(amount);
    }

    public void setDebt(LoanSharkDebt debt) {
        this.debt = debt;
    }

    public int getDebtValue() {
        if (debt != null) return debt.getValue();
        else return 0;
    }

    public int getDebtDays() {
        if (debt != null) return debt.getPaymentPeriod();
        else return 0;
    }

    public int buyStuff(String stuff, int quantity) {
        int totalCost = location.getStuffPrice(stuff) * quantity;

        if (totalCost > cash) {
            return -1;
        } else if (quantity > hold) {
            return -2;
        } else {
            cash -= totalCost;
            int oldQuantityOnHand = stuffOnHand.get(stuff);
            stuffOnHand.put(stuff, oldQuantityOnHand + quantity);
            hold -= quantity;
            return 0;
        }
    }

    public int sellStuff(String stuff, int quantity) {
        int totalIncome = location.getStuffPrice(stuff) * quantity;

        if (stuffOnHand.get(stuff) < quantity) {
            return -1;
        } else if (quantity == 0) {
            return -2;
        } else {
            cash += totalIncome;
            int oldQuantityOnHand = stuffOnHand.get(stuff);
            stuffOnHand.put(stuff, oldQuantityOnHand - quantity);
            hold += quantity;
            return 0;
        }
    }



    public Place getLocation() {
        return location;
    }

    public int getNumberOfGuns() {
        return numberOfGuns;
    }

    public String getGunType() {
        if (gunType != null) {
            return gunType;
        }
        return "None";
    }

    public int getHold() {
        return hold;
    }

    public int getMaxHold() {
        return maxHold;
    }

    public int getHealingCost(){
        return hospital.getHealingCost(this);
    }

    public int getHealingDays(){
        return hospital.getHealingTime(this);
    }

    public int heal(){
        return hospital.heal(this);
    }

    public int borrowMoney(int amount){
        return debt.borrow(this, amount);
    }

    public int payBackDebt(int amount){
        return debt.payBack(this, amount);
    }

    public String translateStuffIndexToName(int index){
        return location.getStuffName(index);
    }

    public int getStuffOnHand(int index) {
        String stuff = translateStuffIndexToName(index);
        return stuffOnHand.get(stuff);
    }

    private void setLocation(Place location){
        this.location = location;
    }

    public int travel(Place destination, int ticketPrice){
        if (ticketPrice > cash) return -1;
        else if(destination == location) return -2;
        else {
            cash -= ticketPrice;
            location = destination;
            return 0;
        }
    }

}
