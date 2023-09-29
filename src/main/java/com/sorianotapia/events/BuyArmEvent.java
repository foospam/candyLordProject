package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.accessories.Arm;
import com.sorianotapia.accessories.ArmContainer;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.ArrayList;

public class BuyArmEvent extends Event {
    public BuyArmEvent(Place place, Player player) {
        super(place, player);
    }

    @Override
    public void run(Controller controller, ScreenFactory screenFactory, ArrayList<String> buffer) {
        if (isLocalEvent()) {
            Arm arm = ArmContainer.getRandomArm();
            buffer.clear();
            buffer.add(arm.getName());
            buffer.add(String.valueOf(arm.getHarm()));
            buffer.add(String.valueOf(arm.getAccuracy()));
            buffer.add(String.valueOf(arm.getPrice()));
            int quantity = (int) (Math.random() * 5) + 1;
            int total_price = quantity * arm.getPrice();
            buffer.add(String.valueOf(quantity));
            buffer.add(String.valueOf(total_price));
            controller.setScreen(screenFactory.ofName(ScreenName.BUY_STUFF_CARRIER));
        }
    }
}
