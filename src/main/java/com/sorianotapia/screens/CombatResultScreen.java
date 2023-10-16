package com.sorianotapia.screens;

import com.sorianotapia.player.Player;

import java.util.LinkedList;

public class CombatResultScreen extends AbstractScreen {

    LinkedList<String> resultStrings;
    boolean battleOver;


    public CombatResultScreen(ScreenName name) {
        super(name);
    }

    @Override
    public String render(Player player) {
        return resultStrings.poll();
    }

    @Override
    public void handleUserInput(Player player) {
        if (!resultStrings.isEmpty()) {

            setNextScreen(this);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (battleOver) {
            setBattleOver(false);
            setNextScreen(ScreenFactory.ofName(ScreenName.EVENT_LOOP));
        } else {
            setNextScreen(ScreenFactory.ofName(ScreenName.COMBAT_ACTION_SELECTION));
        }
    }

    public void setResultStrings(LinkedList<String> resultStrings) {
        this.resultStrings = resultStrings;
    }

    public void setBattleOver(boolean battleOver) {
        this.battleOver = battleOver;
    }

}
