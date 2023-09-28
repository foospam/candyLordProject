package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.ArrayList;

public abstract class Event {
    public final Place place;
    public final Player player;

    public Event(Place place, Player player) {
        this.place = place;
        this.player = player;
    }

    public abstract void run(Controller controller, ScreenFactory screenFactory, ArrayList<String> buffer);

    public boolean isLocalEvent(){
        return place == player.getLocation();
    }


}
