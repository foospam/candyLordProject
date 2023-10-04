package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;

public class EventLoopOneLinerScreen extends AbstractScreen {

    public EventLoopOneLinerScreen(ScreenName name){
        super(name);
    }

    @Override
    public String render(Player player) {
        if (!Controller.inputBuffer.isEmpty()) return String.format(prompt, Controller.inputBuffer.get(0)); // Ojo esto solo vale para un valor
        return super.render(player);
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(ScreenFactory.ofName(ScreenName.EVENT_LOOP));
    }
}
