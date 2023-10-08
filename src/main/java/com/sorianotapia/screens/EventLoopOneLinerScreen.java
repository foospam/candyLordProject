package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

public class EventLoopOneLinerScreen extends AbstractScreen {

    public EventLoopOneLinerScreen(ScreenName name){
        super(name);
    }

    @Override
    public String render(Player player) {

        if (!Controller.inputBuffer.isEmpty()) {
            return String.format(prompt, Controller.inputBuffer.get(0))
                    .concat("\n(Press enter to continue.)"); // Ojo esto solo vale para un valor
        }
        return super.render(player).concat("\n(Press enter to continue.)");
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(ScreenFactory.ofName(ScreenName.EVENT_LOOP));
    }
}