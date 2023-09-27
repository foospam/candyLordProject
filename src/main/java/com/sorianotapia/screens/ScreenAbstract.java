package com.sorianotapia.screens;

import com.sorianotapia.TextContainer;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.headings.HeadingNames;

import java.util.ArrayList;

public abstract class ScreenAbstract {

    ScreenAbstract nextScreen;
    ScreenName name;
    String validInput;
    HeadingNames heading;
    String prompt;
    String badInputPrompt;
    String defaultNextScreen;
    boolean transitionDelay;
    int advanceDay;
    boolean passInput;

    public ScreenAbstract() {

    }

    public String render(ArrayList<String> inputBuffer, Player player) {
        return prompt;
    }

    public ScreenAbstract(ScreenName name) {

        this.name = name;
        this.badInputPrompt = "%s is not a valid input!";
        setTexts();
        setParams();
    }

    public String getValidInput() {
        return this.validInput;
    }

    public abstract void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory);

    public void setNextScreen(ScreenAbstract screen) {
        this.nextScreen = screen;
    }

    public ScreenAbstract getNextScreen() {
        return nextScreen;
    }

    public boolean passInput() {
        return passInput;
    }

    public String getBadInputPrompt(){
        return badInputPrompt;
    }

    public HeadingNames getHeading(){
        return heading;
    }

    public int getAdvanceDay(){
        int advanceDayValue = this.advanceDay;
        resetAdvanceDay();
        return advanceDayValue;
    }

    public void setTexts(){
        validInput = TextContainer.getScreenText(name.name(), "validInput");
        prompt = TextContainer.getScreenText(name.name(), "prompt");
        badInputPrompt = TextContainer.getScreenText(name.name(), "badInputPrompt");
        defaultNextScreen = TextContainer.getScreenText(name.name(), "defaultNextScreen");

  }

    public void setParams() {
        advanceDay = TextContainer.getScreenInt(name.name(), "advanceDay");
        passInput = TextContainer.getScreenBool(name.name(), "passInput");
        transitionDelay = TextContainer.getScreenBool(name.name(), "transitionDelay");
    }

    public void setAdvanceDay(int advanceDay){
        this.advanceDay = advanceDay;
    }

    public void resetAdvanceDay(){
        advanceDay = TextContainer.getScreenInt(name.name(), "advanceDay");
    }

    public boolean getTransitionDelay(){
        return transitionDelay;
    }
}
