package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class GoToHospitalScreen extends AbstractScreen {
    public GoToHospitalScreen(ScreenName name) {

        super(name);
    }

    @Override
    public String render(ArrayList<String> arrayList, Player player){
        return String.format(prompt, player.getHealingCost());
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {
        switch(stringArrayList.get(0)){
            case "Y": {

                int healingDays = player.getHealingDays();
                switch(player.heal()){
                    case 0: {
                        stringArrayList.set(0, String.valueOf(healingDays));
                        setAdvanceDay(healingDays);
                        setNextScreen(screenFactory.ofName(ScreenName.HEALED));
                        break;
                    }
                    case -1: {
                        setNextScreen(screenFactory.ofName(ScreenName.NO_CASH_FOR_HEALING));
                        break;
                    }
                }
                break;


            }
            case "N": {
                setNextScreen(screenFactory.ofName(ScreenName.NOT_HEALED));
                break;
            }
        }

    }
}
