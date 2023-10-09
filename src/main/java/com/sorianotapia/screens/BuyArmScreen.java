package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.accessories.Arm;
import com.sorianotapia.accessories.ArmContainer;
import com.sorianotapia.accessories.StuffCarrier;
import com.sorianotapia.accessories.StuffCarrierContainer;
import com.sorianotapia.fromVersion1.Player;

import java.util.ArrayList;

public class BuyArmScreen extends AbstractScreen {
    public BuyArmScreen(ScreenName name) {
        super(name);
    }

    public String render(Player player) {
        String merchName = Controller.inputBuffer.get(0);
        String merchHarm = Controller.inputBuffer.get(1);
        String merchAccuracy = Controller.inputBuffer.get(2);
        String merchPrice = Controller.inputBuffer.get(3);
        String merchQuantity = Controller.inputBuffer.get(4);
        String merchTotalPrice = Controller.inputBuffer.get(5);
        return String.format(prompt, merchName, merchHarm, merchAccuracy, merchPrice, merchQuantity, merchTotalPrice);
    }

    @Override
    public void handleUserInput(Player player) {
        Arm arm = ArmContainer.getArmByName(Controller.inputBuffer.get(0));
        int quantity = Integer.valueOf(Controller.inputBuffer.get(4));
        if (Controller.inputBuffer.get(6).equals("N")) {
            setNextScreen(ScreenFactory.ofName(ScreenName.BUY_ARM_NO_OK));
        } else {
            switch (player.buyArm(arm, quantity)) {
                case INSUFFICIENT_MONEY -> setNextScreen(ScreenFactory.ofName(ScreenName.NO_MONEY));
                case SUCCESS -> setNextScreen(ScreenFactory.ofName(ScreenName.BUY_ARM_OK));
            }
        }

    }

}
