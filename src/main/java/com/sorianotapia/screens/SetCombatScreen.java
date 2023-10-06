package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.accessories.ArmContainer;
import com.sorianotapia.combat.Accomplice;
import com.sorianotapia.combat.Fighter;
import com.sorianotapia.combat.Policeman;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class SetCombatScreen extends AbstractScreen {

    public SetCombatScreen(ScreenName name) {
        super(name);
    }

    @Override
    public String render(Player player) {

        int numberOfCops = Integer.parseInt(Controller.inputBuffer.get(0));

        return String.format(prompt,
                String.valueOf(numberOfCops),
                numberOfCops == 1 ? "" : "s",
                numberOfCops == 1 ? "is" : "are");
        }

    @Override
    public void handleUserInput(Player player) {
        int numberOfCops = Integer.parseInt(Controller.inputBuffer.get(0));
        int numberOfAllies = Integer.parseInt(Controller.inputBuffer.get(1));

        ArrayList<Fighter> cops = new ArrayList<>();

        for (int i = 0; i < numberOfCops; i++) {
            cops.add(new Policeman(ArmContainer.getRandomArm()));
        }

        ArrayList<Fighter> allies = new ArrayList<>();
        player.setArmInHand(player.getTopGun());
        for (int i = 0; i < numberOfAllies; i++) {
            allies.add(new Accomplice(player.getTopGun(), player.getReputation()));
        }

        nextScreen = ScreenFactory.ofName(ScreenName.COMBAT_ACTION_SELECTION);
        nextScreen
    }
}
