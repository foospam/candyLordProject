package com.sorianotapia.player;

import com.sorianotapia.controller.GameSettings;
import com.sorianotapia.auxiliaries.MethodAnswers;
import com.sorianotapia.auxiliaries.TextContainer;
import com.sorianotapia.goods.Arm;
import com.sorianotapia.goods.DisplaySymbols;
import com.sorianotapia.goods.Holster;
import com.sorianotapia.goods.StuffCarrier;
import com.sorianotapia.combat.Fighter;
import com.sorianotapia.events.EventFactory;
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
    private String icon = DisplaySymbols.PLAYER.toString();
    private boolean isInBattle;

    public Player() {
        hospital = new Hospital();
        health = GameSettings.INITIAL_HEALTH;
        reputation = GameSettings.INITIAL_REPUTATION;
        cash = GameSettings.INITIAL_CASH;
        maxHold = GameSettings.INITIAL_HOLD;
        hold = GameSettings.INITIAL_HOLD;
        holster = new Holster();
        bankAccount = new BankAccount(this);
        location = PlaceContainer.getRandomPlace();
        stuffOnHand = new HashMap<>();
        for (String name : TextContainer.getStuffNames()) stuffOnHand.put(name, 0);
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
            increaseReputationBySale(totalIncome);
            cash += totalIncome;
            int oldQuantityOnHand = stuffOnHand.get(stuff);
            stuffOnHand.put(stuff, oldQuantityOnHand - quantity);
            hold += quantity;
            return MethodAnswers.SUCCESS;
        }
    }

    public MethodAnswers travel(Place destination, int ticketPrice) {
        if (ticketPrice > cash) return MethodAnswers.INSUFFICIENT_MONEY;
        else if (destination == location) return MethodAnswers.SAME_ORIGIN_AND_DESTINATION;
        else {
            cash -= ticketPrice;
            location = destination;
            return MethodAnswers.SUCCESS;
        }
    }

    public MethodAnswers withdrawMoney(int amount) {
        return bankAccount.withdraw(amount);
    }

    public MethodAnswers setDeposits(int amount) {
        return bankAccount.deposit(amount);
    }

    public MethodAnswers heal() {
        return hospital.heal(this);
    }

    public MethodAnswers borrowMoney(int amount) {
        return debt.borrow(this, amount);
    }

    public MethodAnswers payBackDebt(int amount) {
        return debt.payBack(this, amount);
    }

    // Event-related methods

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
            for (int i = 0; i < quantity; i++) {
                holster.add(arm);
            }
            return MethodAnswers.SUCCESS;
        }
    }

    @Override
    public Object[] shootRandomEnemy(ArrayList<Fighter> enemies) {
        Fighter enemy = enemies.get(ThreadLocalRandom.current().nextInt(enemies.size()));
        return armInHand.shoot(enemy, getName());
    }

    @Override
    public Object[] escapeEnemies(ArrayList<Fighter> enemies) {
        for (Fighter enemy : enemies) {
            int fighterRoll = gunRoll();
            int enemyRoll = enemy.gunRoll();
            if (enemyRoll > fighterRoll) {
                return new Object[]{false, getName()};
            }
        }
        isInBattle = false;
        return new Object[]{true, getName()};
    }

    public int gunRoll() {
        return ThreadLocalRandom.current().nextInt(armInHand.getAccuracy())
                + ThreadLocalRandom.current().nextInt(armInHand.getHarm());
    }

    public int harmRoll() {
        return ThreadLocalRandom.current().nextInt(armInHand.getHarm());
    }

    public Arm giveArmInHand() {
        Arm arm = armInHand;
        armInHand = null;
        return arm;
    }

    public void pickArm(Arm arm) {
        holster.add(arm);
    }

    public String combatInfoString() {
        return icon + " You: " + armInHand.toString() + ", " + health + " health points";
    };

    // Auxiliary methods

    public String translateStuffIndexToName(int index) {
        return location.getStuffName(index);
    }

    public void emptyHold() {
        for (String key : stuffOnHand.keySet()) {
            stuffOnHand.put(key, 0);
        }
        hold = maxHold;
    }

    public void emptyPockets() {
        this.cash = 0;
    }

    public void extendPaymentPeriod() {
        debt.extendPaymentPeriod();
    }

    public void increaseReputation() {
        reputation++;
    }

    public void decreaseReputation() {
        reputation--;
    }

    private void increaseReputationBySale(int income) {

        if (reputation > 0 && income + cash + getDeposits() > reputation * 10000) { // Transformar esto en parámetro "CREDIT FACTOR"
            while (reputation * 10000 < income + cash) {
                increaseReputation();
            }
        } else if (reputation == 0 && income > cash + debt.getValue()) {
            increaseReputation();
        }
    }

    // Getters and setters

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {

        if (health > 0) {
            this.health = health;
        } else {
            this.health = 0;
            isInBattle = false;
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

    public int getDeposits() {
        return bankAccount.getDeposits();
    }

    public LoanSharkDebt getDebt(){
        return debt;
    }

    public void setDebt(LoanSharkDebt debt){
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

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) {
        this.hold = hold;
    }

    public int getMaxHold() {
        return maxHold;
    }

    public void setMaxHold(int maxHold) {
        this.maxHold = maxHold;
    }

    public int getHealingCost() {
        return hospital.getHealingCost(this);
    }

    public int getHealingDays() {
        return hospital.getHealingTime(this);
    }

    public int getStuffOnHand(int index) {
        String stuff = translateStuffIndexToName(index);
        return stuffOnHand.get(stuff);
    }

    public void setStuffOnHand(HashMap<String, Integer> stuffOnHand) {
        this.stuffOnHand = stuffOnHand;
    }

    public HashMap<String, Integer> getStuffOnHandMap(){
        return stuffOnHand;
    }

    public Place getLocation() {
        return location;
    }

    public void setLocation(Place location) {
        this.location = location;
    }

    public void setHarm(int harm) {
        setHealth(health - harm);
    }

    public int getOverdue() {
        return debt.getOverdue();
    }

    public void setArmInHand(Arm armInHand) {
        this.armInHand = armInHand;
    }

    @Override
    public String getName() {
        return "You";
    }

    public Arm getTopGun() {
        return holster.getTopGun();
    }

    public boolean isDead() {
        return (health == 0);
    }

    public boolean isInBattle() {
        return isInBattle;
    }
    public Arm getArmInHand() {
        return armInHand;
    }
    public Holster getHolster() {
        return holster;
    };

    public void setHolster(Holster holster) {
        this.holster = holster;
    }

    public void setIsInBattle(boolean inBattle) {
        isInBattle = inBattle;
    }

    public boolean getActiveCredit(){
        return debt.getActiveCredit();
    }

    @Override
    public String toString() {
        return "Player{" +
                "maxHold=" + maxHold +
                ", hold=" + hold +
                ", health=" + health +
                ", hospital=" + hospital +
                ", reputation=" + reputation +
                ", cash=" + cash +
                ", debt=" + debt +
                ", bankAccount=" + bankAccount +
                ", location=" + location +
                ", stuffOnHand=" + stuffOnHand +
                ", armMap=" + armMap +
                ", armInHand=" + armInHand +
                ", holster=" + holster +
                ", icon='" + icon + '\'' +
                ", isInBattle=" + isInBattle +
                '}';
    }
}
