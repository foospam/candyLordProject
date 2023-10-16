package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.TextContainer;
import com.sorianotapia.player.Player;
import org.w3c.dom.Text;

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
