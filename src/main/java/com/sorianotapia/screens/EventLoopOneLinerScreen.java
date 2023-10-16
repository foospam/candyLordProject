package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.auxiliaries.TextContainer;
import com.sorianotapia.player.Player;

public class EventLoopOneLinerScreen extends AbstractScreen {

    public EventLoopOneLinerScreen(ScreenName name){
        super(name);
    }

    @Override
    public String render(Player player) {
        return String.format(prompt, Controller.getDisplayInformationBuffer())
                .concat(TextContainer.getGeneralTexts("enterToContinue"));
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(ScreenFactory.ofName(ScreenName.EVENT_LOOP));
    }
}
