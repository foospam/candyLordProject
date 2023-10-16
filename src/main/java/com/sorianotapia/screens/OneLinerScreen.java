package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;

public class OneLinerScreen extends AbstractScreen {

    public OneLinerScreen(ScreenName name){
        super(name);
    }

    @Override
    public String render(Player player) {
        return String.format(prompt, Controller.getDisplayInformationBuffer())
                .concat("\n(Press enter to continue.)");
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
    }
}
