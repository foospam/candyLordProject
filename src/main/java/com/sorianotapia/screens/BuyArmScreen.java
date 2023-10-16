package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.goods.Arm;
import com.sorianotapia.goods.ArmContainer;
import com.sorianotapia.player.Player;

public class BuyArmScreen extends AbstractScreen {
    public BuyArmScreen(ScreenName name) {
        super(name);
    }

    public String render(Player player) {
        return String.format(prompt, Controller.getDisplayInformationBuffer());
    }

    @Override
    public void handleUserInput(Player player) {
        Arm arm = ArmContainer.getArmByName(Controller.inputBuffer.get(0));
        int quantity = Integer.valueOf(Controller.inputBuffer.get(1));

        if (Controller.inputBuffer.get(2).equals("N")) {
            setNextScreen(ScreenFactory.ofName(ScreenName.BUY_ARM_NO_OK));
        } else {
            switch (player.buyArm(arm, quantity)) {
                case INSUFFICIENT_MONEY -> setNextScreen(ScreenFactory.ofName(ScreenName.NO_MONEY));
                case SUCCESS -> setNextScreen(ScreenFactory.ofName(ScreenName.BUY_ARM_OK));
            }
        }

    }

}
