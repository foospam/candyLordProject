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

    public EventFactory(Place place, Player player){
        events = new ArrayList<>();
        events.add(new BuyStuffCarrierEvent(place, player)); // TO DO, quitar del constructor el place, se puede manejar con setter ssolo
        events.add(new BuyArmEvent(place, player));
        events.add(new MoneyRobberyEvent(place, player));
        events.add(new StuffRobberyEvent(place, player));
        events.add(new PriceDecreaseEvent(place, player));
        events.add(new PriceIncreaseEvent(place, player));
    }

    public void pushRandomEvent(Place place, Player player) {

        if (Math.random() < 0.8) { // Esto se podía meter en un parámetro
            Event event = events.get((int) (Math.random() * events.size()));
            Controller.pushEventMessage(new EventMessage(event, place));
        }
    }

    public void pushRandomEvents(Player player) {
        for (int i = 0; i < PlaceContainer.size(); i++) {
            pushRandomEvent(PlaceContainer.getPlaceByIndex(i), player);
        }
    }
}
