package com.sorianotapia.fromVersion1;

import com.sorianotapia.MethodAnswers;
import com.sorianotapia.accessories.Arm;
import com.sorianotapia.accessories.Holster;
import com.sorianotapia.accessories.StuffCarrier;
import com.sorianotapia.combat.Fighter;
import com.sorianotapia.events.EventFactory;
import com.sorianotapia.places.NameContainer;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements Fighter {
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
    private HashMap<Arm, Integer> armMap;
    private Arm armInHand;
    private Holster holster;


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {

        if (health > 0) {
            this.health = health;
        }
        else {
            this.health = 0;
            EventFactory.pushGameOverEvent();
        }
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

        holster = new Holster();
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

    public MethodAnswers buyStuffCarrier(StuffCarrier carrier) {
        int hold = carrier.getHold();
        int price = carrier.getPrice();

        if (price > cash) {
            return MethodAnswers.INSUFFICIENT_MONEY;
        } else {
            cash -= price;
            this.maxHold = hold;
            return MethodAnswers.SUCCESS;
        }
    }


    public MethodAnswers buyArm(Arm arm, int quantity) {
        int totalPrice = arm.getPrice() * quantity;

        if (totalPrice > cash) {
            return MethodAnswers.INSUFFICIENT_MONEY;
        } else {
            cash -= totalPrice;
            int armNumber;
            if (armMap.containsKey(arm)) {
                armNumber = armMap.get(arm) + quantity;
            } else {
                armNumber = quantity;
            }
            armMap.put(arm, armNumber);
            return MethodAnswers.SUCCESS;
            // TO DO: Aquí hay que hacer una armería para poderlas llevar mejor, las armas. Por ejemplo, para el string, o para cuando haya que elegirlas en la pelea.
        }
    }

    public MethodAnswers buyStuff(String stuff, int quantity) {
        int totalCost = location.getStuffPrice(stuff) * quantity;

        if (totalCost > cash) {
            return MethodAnswers.INSUFFICIENT_MONEY;
        } else if (quantity > hold) {
            return MethodAnswers.INSUFFICIENT_HOLD;
        } else {
            cash -= totalCost;
            int oldQuantityOnHand = stuffOnHand.get(stuff);
            stuffOnHand.put(stuff, oldQuantityOnHand + quantity);
            hold -= quantity;
            return MethodAnswers.SUCCESS;
        }
    }

    public MethodAnswers sellStuff(String stuff, int quantity) {
        int totalIncome = location.getStuffPrice(stuff) * quantity;

        if (stuffOnHand.get(stuff) < quantity) {
            return MethodAnswers.INSUFFICIENT_STASH;
        } else if (quantity == 0) {
            return MethodAnswers.QUANTITY_ZERO;
        } else {
            cash += totalIncome;
            int oldQuantityOnHand = stuffOnHand.get(stuff);
            stuffOnHand.put(stuff, oldQuantityOnHand - quantity);
            hold += quantity;
            return MethodAnswers.SUCCESS;
        }
    }

    public Place getLocation() {
        return location;
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

    public MethodAnswers borrowMoney(int amount){
        return debt.borrow(this, amount);
    }

    public MethodAnswers payBackDebt(int amount){
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

    public MethodAnswers travel(Place destination, int ticketPrice){
        if (ticketPrice > cash) return MethodAnswers.INSUFFICIENT_MONEY;
        else if(destination == location) return MethodAnswers.SAME_ORIGIN_AND_DESTINATION;
        else {
            cash -= ticketPrice;
            location = destination;
            return MethodAnswers.SUCCESS;
        }
    }

    public void emptyPockets(){
        this.cash = 0;
    }

    public void emptyHold(){
        for (String key: stuffOnHand.keySet()){
            stuffOnHand.put(key, 0);
        }
        hold = maxHold;
    }

    public void setHarm(int harm){
        setHealth(health-harm);
    }

    public int getOverdue() {
        return debt.getOverdue();
    }

    public void extendPaymentPeriod(){
        debt.extendPaymentPeriod();
    }

    @Override
    public String shootRandomEnemy(ArrayList<Fighter> enemies) {
        Fighter enemy = enemies.get(ThreadLocalRandom.current().nextInt(enemies.size()));
        return armInHand.shoot(enemy);
    }

    @Override
    public String escapeEnemies(ArrayList<Fighter> enemies) {
        for (Fighter enemy : enemies) {
            int fighterRoll = gunRoll();
            int enemyRoll = enemy.gunRoll();
            if (enemyRoll > fighterRoll) {
                return "You tried to chicken out, but the cops were quicker. Good luck next time!";
            }
        }
        return "You chickened out like the chicken you are!";
    }

    public int gunRoll(){
        return ThreadLocalRandom.current().nextInt(armInHand.getAccuracy())
                + ThreadLocalRandom.current().nextInt(armInHand.getHarm());
    }

    public int harmRoll(){
        return ThreadLocalRandom.current().nextInt(armInHand.getHarm());
    }

    public void setArmInHand(Arm armInHand) {
        this.armInHand = armInHand;
    }

    @Override
    public String getName() {
        return "You";
    }

    public Arm getTopGun(){
        return holster.getTopGun();
    }

    public boolean isDead(){
        return (health == 0);
    }

    public boolean isInBattle(){
        return true;
    }
}
