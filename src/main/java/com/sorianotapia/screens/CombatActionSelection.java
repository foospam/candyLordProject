package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.accessories.Arm;
import com.sorianotapia.combat.Accomplice;
import com.sorianotapia.combat.Fighter;
import com.sorianotapia.combat.Policeman;
import com.sorianotapia.fromVersion1.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class CombatActionSelection extends AbstractScreen {

    private ArrayList<Fighter> cops;

    private ArrayList<Fighter> allies;
    private LinkedList<String> fightEvents;


    @Override
    public String render(Player player) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prompt+"\n\nTHIS IS THE SITUATION\n");
        stringBuilder.append(player.combatInfoString()+"\n");
        for (Fighter fighter: allies) {
            stringBuilder.append(fighter.combatInfoString()+"\n");
        }
        for (Fighter fighter: cops) {
            stringBuilder.append(fighter.combatInfoString()+"\n");
        }
        return stringBuilder.toString();
    }

    public void setAllies(ArrayList<Fighter> allies) {
        this.allies = allies;
    }

    public void setCops(ArrayList<Fighter> cops) {
        this.cops = cops;
    }

    public CombatActionSelection(ScreenName name) {
        super(name);
    }


    @Override
    public void handleUserInput(Player player) {
        LinkedList<String> resultStrings = new LinkedList<>();
        CombatResultScreen next = (CombatResultScreen) ScreenFactory.ofName(ScreenName.COMBAT_RESULT);

        String fightDecision = Controller.inputBuffer.get(0);

        System.out.println("Cops: " + cops.size());

        switch (fightDecision) {
            case "G" -> {
                String escaped = player.escapeEnemies(cops);
                if (!escaped.contains("tried")) {
                    resultStrings.add(escaped);
                    next.setBattleOver(true);
                }
            }
            case "F" -> {
                if (player.getArmInHand() == null) {
                    Arm topGun = player.getTopGun();
                    System.out.println("Topgun is null: " + (topGun == null));
                    player.setArmInHand(player.getTopGun());
                }
                resultStrings.add(player.shootRandomEnemy(cops));

                // Remove cops killed by player and increase reputation for them

                for (int i = cops.size() - 1; i >= 0; i--) {
                    if (cops.get(i).isDead()) {
                        resultStrings.add(cops.get(i).getName() + " has bitten the dust!");
                        player.increaseReputation();
                        cops.remove(i);
                    }
                }
            }
        }

        // Stop battle if the main character has fled

        if (next.battleOver) {
            player.decreaseReputation();
            next.setResultStrings(resultStrings);
            setNextScreen(next);
            return;
        }

        // Allies attack

        if (!cops.isEmpty()) {
            for (Fighter ally : allies) {
                Accomplice accomplice = (Accomplice) ally;
                resultStrings.add((accomplice.act(cops)));

                // Remove dead characters from cop list:
                for (int i = cops.size() - 1; i >= 0; i--) {
                    if (cops.get(i).isDead()) {
                        resultStrings.add(cops.get(i).getName() + " has bitten the dust!");
                        cops.remove(i);
                    }
                }

                if (cops.isEmpty()) {
                    break;
                }
            }
        }

        // Remove characters who have fled battle from the allies list

        for (int i = allies.size() - 1; i >= 0; i--) {
            if (!allies.get(i).isInBattle()) {
                allies.remove(i);
            }
        }

        // The cops attack

        allies.add(player); // The player can be hit by the police

        COP_ATTACK:
        for (Fighter cop : cops) {
            Policeman policeman = (Policeman) cop;
            resultStrings.add((policeman.act(allies)));

            // Remove dead characters from allies list:
            for (int i = allies.size() - 1; i >= 0; i--) {
                if (player.isDead()) {
                    resultStrings.add("You have bitten the dust!");
                    break COP_ATTACK;
                }
                if (allies.get(i).isDead()) {
                    resultStrings.add(allies.get(i).getName() + " has bitten the dust!");
                    allies.remove(i);
                }
            }
        }

        // Remove player from allies list

        allies.remove(player);

        // Remove characters who have fled battle from the cop list

        for (int i = cops.size() - 1; i >= 0; i--) {
            if (!cops.get(i).isInBattle()) {
                cops.remove(i);
            }
        }

        if (cops.isEmpty()) {
            player.getReputation();
            resultStrings.add("You have won!");
            for (Fighter fighter : allies) {
                Arm arm = fighter.giveArmInHand();
                if (!arm.isDefault()) {
                    player.pickArm(arm);
                    resultStrings.add("You retrieved a " + arm.getName() + " from the battle field.");
                }
            }
            next.setBattleOver(true);
        } else if (player.isDead()) {
            next.setBattleOver(true);
        }

        next.setResultStrings(resultStrings);
        setNextScreen(next);
    }

}
