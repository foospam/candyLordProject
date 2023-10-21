package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.auxiliaries.TextContainer;
import com.sorianotapia.controller.GameSettings;
import com.sorianotapia.player.Player;

public class OneLinerScreen extends AbstractScreen {

    public OneLinerScreen(ScreenName name){
        super(name);
        validInput = "^((?!"+ GameSettings.FALSE_INPUT_TOKEN+")(.|))*$";
    }

    @Override
    public String render(Player player) {
        return String.format(prompt, Controller.getDisplayInformationBuffer())
                .concat(TextContainer.getGeneralTexts("enterToContinue"));
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(ScreenFactory.ofName(ScreenName.MAIN_SELECTION));
    }
}
