package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.headings.HeadingNames;

import java.util.ArrayList;

public class MainSelectionScreen extends AbstractScreen {

    public MainSelectionScreen(ScreenName name) {
        super(name);
        this.heading = HeadingNames.STAT_PAGE_DRUGS;
        this.validInput = "[BSJVHLR]";
        this.prompt = """
                Here's a list of your possible plans dude:
                (B)uy Drugs                 (V)isit the bank
                (S)ell Drugs                Go to the (H)ospital
                (J)et to another city       See the (L)oan shark
                (R)elax and do nothing
                
                Make up your mind!
                """;
    }

    @Override
    public void handleUserInput(ArrayList<String> input, Player player, ScreenFactory screenFactory) {
        setNextScreen(switch(input.get(0)) {
            case "B" -> screenFactory.ofName(ScreenName.SELECT_DRUG_TO_BUY);
            case "S" -> screenFactory.ofName(ScreenName.SELECT_DRUG_TO_SELL);
            case "J" -> screenFactory.ofName(ScreenName.TRAVEL);
            case "V" -> screenFactory.ofName(ScreenName.BANK_OPERATION_SELECTION);
            case "H" -> screenFactory.ofName(ScreenName.GO_TO_HOSPITAL);
            case "L" -> screenFactory.ofName(ScreenName.LOAN_SHARK);
            case "R" -> screenFactory.ofName(ScreenName.RELAX);
            default -> screenFactory.ofName(ScreenName.MAIN_SELECTION);
        });
    }

}
