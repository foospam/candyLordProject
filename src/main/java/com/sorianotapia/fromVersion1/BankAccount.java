package com.sorianotapia.fromVersion1;

import com.sorianotapia.MethodAnswers;

import java.lang.reflect.Method;

public class BankAccount {
    private int deposits;
    private Player player;

    public BankAccount(Player player){
        this.player = player;
    }

    public MethodAnswers withdraw(int amount){
        if (amount <= deposits) {
            player.setCash(player.getCash() + amount);
            deposits -= amount;
            return MethodAnswers.SUCCESS;
        }
        return MethodAnswers.INSUFFICIENT_MONEY;
    }

    public MethodAnswers deposit(int amount){
        if (player.getCash() >= amount){
            player.setCash(player.getCash() - amount);
            deposits += amount;
            return MethodAnswers.SUCCESS;
        }
        return MethodAnswers.INSUFFICIENT_MONEY;
    }

    public int getDeposits() {
        return deposits;
    }
}
