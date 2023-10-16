package com.sorianotapia.events;

import com.sorianotapia.player.Player;
import com.sorianotapia.places.Place;

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
