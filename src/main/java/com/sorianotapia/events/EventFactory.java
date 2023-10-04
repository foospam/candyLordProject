package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

import java.util.ArrayList;

public class EventFactory {

    static private ArrayList<Event> randomPlaceEvents = new ArrayList<>();
    static private ArrayList<Event> randomUserEvents = new ArrayList<>();
    static private Event reclaimDebtEvent;

    static private GameOverEvent gameOverEvent;

    private EventFactory(){};

    public static void initializeEvents(Player player){
        randomPlaceEvents.add(new PriceDecreaseEvent(player));
        randomPlaceEvents.add(new PriceIncreaseEvent(player));

        randomUserEvents.add(new BuyStuffCarrierEvent(player)); // TO DO, quitar del constructor el place, se puede manejar con setter ssolo
        randomUserEvents.add(new BuyArmEvent(player));
        randomUserEvents.add(new MoneyRobberyEvent(player));
        randomUserEvents.add(new StuffRobberyEvent(player));

        reclaimDebtEvent = new ReclaimDebtEvent(player);

        gameOverEvent = new GameOverEvent(player);
    }



    public static void pushRandomEvent(Place place, Player player) {

        if (Math.random() < 0.8) { // Esto se podía meter en un parámetro
            Event event = randomPlaceEvents.get((int) (Math.random() * randomPlaceEvents.size()));
            Controller.pushEventMessage(new EventMessage(event, place));
        }
    }

    public static void pushRandomEvents(Player player) {
        for (int i = 0; i < PlaceContainer.size(); i++) {
            pushRandomEvent(PlaceContainer.getPlaceByIndex(i), player);
        }
    }

    public static void pushDebtEvent(){
        Controller.pushEventMessage(new EventMessage(reclaimDebtEvent, null));
    }

    public static void pushGameOverEvent(){
        System.out.println("Game over triggered");
        Controller.pushEventMessage(new EventMessage(gameOverEvent, null));
    }
}
