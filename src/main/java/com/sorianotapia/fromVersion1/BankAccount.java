package com.sorianotapia.fromVersion1;

public class BankAccount {
    private int deposits;
    private Player player;

    public BankAccount(Player player){
        this.player = player;
    }

    public int withdraw(int amount){
        if (amount <= deposits) {
            player.setCash(player.getCash() + amount);
            deposits -= amount;
            return 0;
        }
        return -1;
    }

    public int deposit(int amount){
        if (player.getCash() >= amount){
            player.setCash(player.getCash() - amount);
            deposits += amount;
            return 0;
        }
        return -1;
    }

    public int getDeposits() {
        return deposits;
    }
}
