package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;

import java.util.ArrayList;

public abstract class UserEvent implements Event {

    Player player;

    public UserEvent(Player player) {
        this.player = player;
    }

    @Override
    public boolean isLocalEvent() {
        return true;
    }

    @Override
    public void setPlace(Place place) {
    }

    @Override
    public void setPlayer(Player player) {
    }
}
