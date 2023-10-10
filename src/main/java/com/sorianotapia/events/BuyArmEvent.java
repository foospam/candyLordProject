package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.accessories.Arm;
import com.sorianotapia.accessories.ArmContainer;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.ArrayList;

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
