package com.sorianotapia.events;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.goods.Arm;
import com.sorianotapia.goods.ArmContainer;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

public class BuyArmEvent extends UserEvent {
    public BuyArmEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {
        if (isLocalEvent()) {
            Arm arm = ArmContainer.getRandomArm();

            int quantity = (int) (Math.random() * 5) + 1;
            int totalPrice = quantity * arm.getPrice();

            Controller.setDisplayInformationBuffer(
                    new Object[]{
                            arm.getName(),
                            arm.getHarm(),
                            arm.getAccuracy(),
                            arm.getPrice(),
                            quantity,
                            totalPrice
                    }
            );

            Controller.inputBuffer.clear();
            Controller.inputBuffer.add(arm.getName());
            Controller.inputBuffer.add(String.valueOf(quantity));

            controller.setScreen(ScreenFactory.ofName(ScreenName.BUY_ARM));
        }
    }
}
