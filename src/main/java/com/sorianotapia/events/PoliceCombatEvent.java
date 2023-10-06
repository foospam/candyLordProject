package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.concurrent.ThreadLocalRandom;

public class PoliceCombatEvent implements Event {


    @Override
    public void run(Controller controller) {
        int policeAgents = ThreadLocalRandom.current().nextInt(1,5);
        Controller.inputBuffer.clear();
        Controller.inputBuffer.add(String.valueOf(policeAgents));
        controller.setScreen(ScreenFactory.ofName(ScreenName.SET_COMBAT));
    }

    @Override
    public boolean isLocalEvent() {
        return false;
    }

    @Override
    public void setPlace(Place place) {

    }

    @Override
    public void setPlayer(Player player) {

    }
}
