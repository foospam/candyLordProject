package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.util.ArrayList;

public interface Event {


    public abstract void run(Controller controller);

    public boolean isLocalEvent();

    public void setPlace(Place place);
    public void setPlayer(Player player);
}
