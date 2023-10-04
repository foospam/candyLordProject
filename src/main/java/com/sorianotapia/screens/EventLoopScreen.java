package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;

public class EventLoopScreen extends AbstractScreen {

    @Override
    public String render(Player player) {
        return null;
    }

    public EventLoopScreen(ScreenName name) {
        super(name);
    }

    @Override
    public void handleUserInput(Player player) {
        setNextScreen(this);
    }
}
