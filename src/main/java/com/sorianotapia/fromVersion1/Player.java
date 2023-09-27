package com.sorianotapia.fromVersion1;

import java.util.EnumMap;

public class Player {
    private int maxHold;
    private int hold;
    private int health;
    private Hospital hospital;
    private int reputation;
    private int cash;
    private LoanSharkDebt debt;
    private BankAccount bankAccount;
    private City location;
    private EnumMap<Drugs, Integer> drugsOnHand;

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
        drugsOnHand = new EnumMap<>(Drugs.class);
        for (Drugs drug : Drugs.values()) drugsOnHand.put(drug, 0);
        location = City.values()[(int) (Math.random() * 8)];
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

    public int buyDrugs(Drugs drug, int quantity) {
        int totalCost = location.getDrugPrice(drug) * quantity;

        if (totalCost > cash) {
            return -1;
        } else if (quantity > hold) {
            return -2;
        } else {
            cash -= totalCost;
            int oldQuantityOnHand = drugsOnHand.get(drug);
            drugsOnHand.put(drug, oldQuantityOnHand + quantity);
            hold -= quantity;
            return 0;
        }
    }

    public int sellDrugs(Drugs drug, int quantity) {
        int totalIncome = location.getDrugPrice(drug) * quantity;

        if (drugsOnHand.get(drug) < quantity) {
            return -1;
        } else if (quantity == 0) {
            return -2;
        } else {
            cash += totalIncome;
            int oldQuantityOnHand = drugsOnHand.get(drug);
            drugsOnHand.put(drug, oldQuantityOnHand - quantity);
            hold += quantity;
            return 0;
        }
    }


//    public void travel(City city){
//        int fromIndex = location.ordinal();
//        int toIndex = city.ordinal();
//        int ticketPrice = JetTickets.getPrice(fromIndex, toIndex);
//        setCash(getCash()-ticketPrice);
//        this.location = city;
//        this.location.updateDrugPrices();
//    }

    public EnumMap<Drugs, Integer> getDrugsOnHand() {
        return drugsOnHand;
    }

    public int getDrugsOnHand(Drugs drug) {
        return drugsOnHand.get(drug);
    }

    public City getLocation() {
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
}
