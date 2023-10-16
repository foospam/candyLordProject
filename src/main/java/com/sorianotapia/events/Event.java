package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.player.Player;
import com.sorianotapia.places.Place;

public interface Event {


    public abstract void run(Controller controller);

    public boolean isLocalEvent();

    public void setPlace(Place place);
    public void setPlayer(Player player);
}
