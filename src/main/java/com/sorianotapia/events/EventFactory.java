package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

import java.util.ArrayList;

public class EventFactory {


    ArrayList<Event> events;

    public static void main(String[] args) {
    }

    public void pushRandomEvent(Place place, Player player) {
        if (Math.random() < 0.3) {
            //int eventIndex = (int) (Math.random() * events.size());
            Controller.pushEvent(new PriceIncreaseEvent(place, player));
        }
    }

    public void pushRandomEvents(Player player) {
        for (int i = 0; i < PlaceContainer.size(); i++) {
            pushRandomEvent(PlaceContainer.getPlaceByIndex(i), player);
        }
    }
}
