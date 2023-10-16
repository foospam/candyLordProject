package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.GameInfo;
import com.sorianotapia.player.Player;

public class MainSelectionScreen extends AbstractScreen {

    public MainSelectionScreen(ScreenName name) {
        super(name);
        heading = true;
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(switch(Controller.inputBuffer.get(0)) {
            case "B" -> {
                Controller.setDisplayInformationBuffer(
                        new Object[]{
                                GameInfo.gameInfo.getStuffNames().get(0),
                                GameInfo.gameInfo.getStuffNames().get(1),
                                GameInfo.gameInfo.getStuffNames().get(2),
                                GameInfo.gameInfo.getStuffNames().get(3),
                                GameInfo.gameInfo.getStuffNames().get(4),
                                GameInfo.gameInfo.getStuffNames().get(5),
                                GameInfo.gameInfo.getStuffNames().get(6),
                                GameInfo.gameInfo.getStuffNames().get(7),
                        });
                yield ScreenFactory.ofName(ScreenName.SELECT_DRUG_TO_BUY);}
            case "S" -> {
                Controller.setDisplayInformationBuffer(
                        new Object[]{
                                GameInfo.gameInfo.getStuffNames().get(0),
                                GameInfo.gameInfo.getStuffNames().get(1),
                                GameInfo.gameInfo.getStuffNames().get(2),
                                GameInfo.gameInfo.getStuffNames().get(3),
                                GameInfo.gameInfo.getStuffNames().get(4),
                                GameInfo.gameInfo.getStuffNames().get(5),
                                GameInfo.gameInfo.getStuffNames().get(6),
                                GameInfo.gameInfo.getStuffNames().get(7),
                        });
                yield ScreenFactory.ofName(ScreenName.SELECT_DRUG_TO_SELL);}
            case "J" -> ScreenFactory.ofName(ScreenName.TRAVEL);
            case "V" -> ScreenFactory.ofName(ScreenName.BANK_OPERATION_SELECTION);
            case "H" -> ScreenFactory.ofName(ScreenName.GO_TO_HOSPITAL);
            case "L" -> ScreenFactory.ofName(ScreenName.LOAN_SHARK);
            case "R" -> ScreenFactory.ofName(ScreenName.RELAX);
            case "Q" -> ScreenFactory.ofName(ScreenName.QUIT_CONFIRMATION);
            default -> ScreenFactory.ofName(ScreenName.MAIN_SELECTION);
        });
    }

}
