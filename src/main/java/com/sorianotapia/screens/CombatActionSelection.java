package com.sorianotapia.screens;

import com.sorianotapia.Controller;
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


    public void setAllies(ArrayList<Fighter> allies) {
        this.allies = allies;
    }

    public void setCops(ArrayList<Fighter> cops){
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
                String escaped = player.escapeEnemies(cops);
                if (!escaped.contains("tried")) {
                    resultStrings.add(escaped);
                    next.setBattleOver(true);
                }
            }
            case "F" -> {
                resultStrings.add(player.shootRandomEnemy(cops));
            }
        }

        // Stop battle if the main character has fled

        if (next.battleOver) {
            next.setResultStrings(resultStrings);
            setNextScreen(next);
            return;
        }

        // Allies attack

        for (Fighter ally : allies) {
            Accomplice accomplice = (Accomplice) ally;
            resultStrings.add((accomplice.act(cops)));

            // Remove dead characters from cop list:
            for (int i = cops.size()-1; i <= 0; i--) {
                if (cops.get(i).isDead()) {
                    resultStrings.add(cops.get(i).getName() + " has bitten the dust!");
                    cops.remove(i);
                }
            }
        }

        // Remove characters who have fled battle from the allies list

        for (int i = allies.size()-1; i <= 0; i--) {
            if (!allies.get(i).isInBattle()) {
                allies.remove(i);
            }
        }

        // The cops attack

        for (Fighter cop : cops) {
            Policeman policeman = (Policeman) cop;
            allies.add(player);
            resultStrings.add((policeman.act(allies)));

            // Remove dead characters from allies list:
            for (int i = allies.size()-1; i <= 0; i--) {
                if (allies.get(i).isDead()) {
                    resultStrings.add(allies.get(i).getName() + " has bitten the dust!");
                    allies.remove(i);
                }
            }
        }

        // Remove player from allies list

        allies.remove(player);

        // Remove characters who have fled battle from the cop list

        for (int i = cops.size()-1; i <= 0; i--) {
            if (!cops.get(i).isInBattle()) {
                cops.remove(i);
            }
        }

        next.setResultStrings(resultStrings);
        setNextScreen(next);
    }

}
