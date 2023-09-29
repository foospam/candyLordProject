package com.sorianotapia.screens;

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

    public String render(ArrayList<String> inputBuffer, Player player) {
        String merchName = inputBuffer.get(0);
        String merchHarm = inputBuffer.get(1);
        String merchAccuracy = inputBuffer.get(2);
        String merchPrice = inputBuffer.get(3);
        String merchQuantity = inputBuffer.get(4);
        String merchTotalPrice = inputBuffer.get(5);
        return String.format(prompt, merchName, merchHarm, merchAccuracy, merchPrice, merchQuantity, merchTotalPrice);
    }

    @Override
    public void handleUserInput(ArrayList<String> inputBuffer, Player player, ScreenFactory screenFactory) {
        Arm arm = ArmContainer.getArmByName(inputBuffer.get(0));
        int quantity = Integer.valueOf(inputBuffer.get(4));
        if (inputBuffer.get(6) == "N") {
            setNextScreen(screenFactory.ofName(ScreenName.BUY_ARM_NO_OK));
        } else {
            switch (player.buyArm(arm, quantity)) {
                case INSUFFICIENT_MONEY -> setNextScreen(screenFactory.ofName(ScreenName.NO_MONEY));
                case SUCCESS -> setNextScreen(screenFactory.ofName(ScreenName.BUY_ARM_OK));
            }
        }

    }

}
