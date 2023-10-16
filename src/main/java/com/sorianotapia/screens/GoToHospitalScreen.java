package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;

public class GoToHospitalScreen extends AbstractScreen {
    public GoToHospitalScreen(ScreenName name) {
        super(name);
    }

    @Override
    public String render(Player player) {
        return String.format(prompt, player.getHealingCost());
    }

    @Override
    public void handleUserInput(Player player) {
        switch (Controller.inputBuffer.get(0)) {
            case "Y" -> {

                int healingDays = player.getHealingDays();
                switch (player.heal()) {
                    case SUCCESS -> {
                        Controller.setDisplayInformationBuffer(new Object[]{healingDays});
                        setAdvanceDay(healingDays);
                        setNextScreen(ScreenFactory.ofName(ScreenName.HEALED));
                    }
                    case INSUFFICIENT_MONEY -> {
                        setNextScreen(ScreenFactory.ofName(ScreenName.NO_CASH_FOR_HEALING));

                    }
                }
            }
            case "N" -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.NOT_HEALED));
            }
        }

    }
}
