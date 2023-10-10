package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.TextContainer;
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
        stringBuilder.append(player.combatInfoString() + "\n");
        for (Fighter fighter : allies) {
            stringBuilder.append(fighter.combatInfoString() + "\n");
        }
        for (Fighter fighter : cops) {
            stringBuilder.append(fighter.combatInfoString() + "\n");
        }
        return String.format(prompt, stringBuilder.toString());
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

        switch (fightDecision) {
            case "G" -> {
                player.escapeEnemies(cops);
                if (!player.isInBattle()) {
                    resultStrings.add(TextContainer.getBattlePrompts("youEscaped"));
                    next.setBattleOver(true);
                } else {
                    resultStrings.add(TextContainer.getBattlePrompts("youEscapedNot"));
                }
            }
            case "F" -> {
                if (player.getArmInHand() == null) {
                    Arm topGun = player.getTopGun();
                    player.setArmInHand(topGun);
                }
                resultStrings.add(getBattleResultString(player.shootRandomEnemy(cops)));

                // Remove cops killed by player and increase reputation for them

                for (int i = cops.size() - 1; i >= 0; i--) {
                    if (cops.get(i).isDead()) {
                        resultStrings.add(String.format(TextContainer.getBattlePrompts("killed"), cops.get(i).getName()));
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
                resultStrings.add(getBattleResultString(accomplice.act(cops)));

                // Remove dead characters from cop list:
                for (int i = cops.size() - 1; i >= 0; i--) {
                    if (cops.get(i).isDead()) {
                        resultStrings.add(String.format(TextContainer.getBattlePrompts("killed"), cops.get(i).getName()));
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
            resultStrings.add(getBattleResultString(policeman.act(allies)));

            // Remove dead characters from allies list:
            for (int i = allies.size() - 1; i >= 0; i--) {
                if (player.isDead()) {
                    resultStrings.add(TextContainer.getBattlePrompts("youKilled"));
                    break COP_ATTACK;
                }
                if (allies.get(i).isDead()) {
                    resultStrings.add(String.format(TextContainer.getBattlePrompts("killed"), allies.get(i).getName()));
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
            player.setIsInBattle(false);
            player.getReputation();
            resultStrings.add(TextContainer.getBattlePrompts("youWin"));
            for (Fighter fighter : allies) {
                Arm arm = fighter.giveArmInHand();
                if (!arm.isDefault()) {
                    player.pickArm(arm);
                    resultStrings.add(String.format(TextContainer.getBattlePrompts("retrieveArm"), arm.getName()));
                }
            }
            next.setBattleOver(true);
        } else if (player.isDead()) {
            next.setBattleOver(true);
        }

        next.setResultStrings(resultStrings);
        setNextScreen(next);
    }

    private String getBattleResultString(Object[] result) {
        if (result.length == 3) {
            if ((int) result[2] == -1) {
                return String.format(TextContainer.getBattlePrompts("miss"),
                        result[0],
                        result[1],
                        result[0]);
            } else {
                return String.format(TextContainer.getBattlePrompts("hit"),
                        result[0],
                        result[1],
                        result[1],
                        result[2]);
            }
        } else {
            if ((boolean) result[0] == true) {
                return String.format(TextContainer.getBattlePrompts("escaped"),
                        result[1]
                );
            } else {
                return String.format(TextContainer.getBattlePrompts("escapedNot"),
                        result[1]);
            }
        }
    }
}

