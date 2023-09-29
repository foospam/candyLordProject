package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class GoToHospitalScreen extends AbstractScreen {
    public GoToHospitalScreen(ScreenName name) {

        super(name);
    }

    @Override
    public String render(Player player){
        return String.format(prompt, player.getHealingCost());
    }

    @Override
    public void handleUserInput(Player player) {
        switch(Controller.inputBuffer.get(0)){
            case "Y": {

                int healingDays = player.getHealingDays();
                switch(player.heal()){
                    case 0: {
                        Controller.inputBuffer.set(0, String.valueOf(healingDays));
                        setAdvanceDay(healingDays);
                        setNextScreen(ScreenFactory.ofName(ScreenName.HEALED));
                        break;
                    }
                    case -1: {
                        setNextScreen(ScreenFactory.ofName(ScreenName.NO_CASH_FOR_HEALING));
                        break;
                    }
                }
                break;


            }
            case "N": {
                setNextScreen(ScreenFactory.ofName(ScreenName.NOT_HEALED));
                break;
            }
        }

    }
}
