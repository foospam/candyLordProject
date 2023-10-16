package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.concurrent.ThreadLocalRandom;

public class PoliceCombatEvent extends UserEvent {

    public PoliceCombatEvent(Player player) {
        super(player);
    }

    @Override
    public void run(Controller controller) {
        int policeAgents = player.getReputation() + ThreadLocalRandom.current().nextInt(0,3);
        Controller.setDisplayInformationBuffer(new Object[]{policeAgents, player.getReputation()});
        Controller.inputBuffer.clear();
        Controller.inputBuffer.add(String.valueOf(policeAgents));
        controller.setScreen(ScreenFactory.ofName(ScreenName.SET_COMBAT));
    }

}
