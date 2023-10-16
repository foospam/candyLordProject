package com.sorianotapia.events;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.controller.GameSettings;
import com.sorianotapia.player.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EventFactory {

    static private ArrayList<EventName> randomPlaceEvents = new ArrayList<>();
    static private ArrayList<EventName> randomUserEvents = new ArrayList<>();
    static private Event reclaimDebtEvent;

    static private GameOverEvent gameOverEvent;

    private EventFactory() {
    }

    ;

    public static void initializeEvents(Player player) {
        randomPlaceEvents.add(EventName.PRICE_INCREASE_EVENT);
        randomPlaceEvents.add(EventName.PRICE_DECREASE_EVENT);

        randomUserEvents.add(EventName.BUY_STUFF_CARRIER_EVENT); // TO DO, quitar del constructor el place, se puede manejar con setter ssolo
        randomUserEvents.add(EventName.BUY_ARM_EVENT);
        randomUserEvents.add(EventName.MONEY_ROBBERY_EVENT);
        randomUserEvents.add(EventName.STUFF_ROBBERY_EVENT);
        randomUserEvents.add(EventName.POLICE_COMBAT_EVENT);

        reclaimDebtEvent = new ReclaimDebtEvent(player);

        gameOverEvent = new GameOverEvent(player);
    }


    public static void pushRandomPlaceEvent(Place place, Player player) {

        if (Math.random() < GameSettings.RANDOM_PLACE_EVENT_FREQ) {
            Controller.pushEventMessage(new EventMessage(
                    ofName(randomPlaceEvents.get(ThreadLocalRandom.current().nextInt(randomPlaceEvents.size())),
                            player),
                    place));
        }
    }

    public static void pushRandomUserEvents(Player player) {

        if (Math.random() < GameSettings.RANDOM_USER_EVENT_FREQ) {
            Controller.pushEventMessage(new EventMessage(
                    ofName(randomUserEvents.get(ThreadLocalRandom.current().nextInt(randomUserEvents.size())),
                            player),
                    player.getLocation()));
        }
    }

    public static void pushRandomPlaceEvents(Player player) {
        for (int i = 0; i < PlaceContainer.size(); i++) {
            pushRandomPlaceEvent(PlaceContainer.getPlaceByIndex(i), player);
        }
    }

    public static void pushDailyPriceUpdateEvent(Player player){
        Controller.pushEventMessage(new EventMessage(new UpdatePricesDailyEvent(player), null));
    }

    public static void pushDebtEvent() {
        Controller.pushEventMessage(new EventMessage(reclaimDebtEvent, null));
    }

    public static void pushGameOverEvent() {
        Controller.pushEventMessage(new EventMessage(gameOverEvent, null));
    }

    private static Event ofName(EventName name, Player player) {
        return switch (name) {
            case PRICE_DECREASE_EVENT -> new PriceDecreaseEvent(player);
            case PRICE_INCREASE_EVENT -> new PriceIncreaseEvent(player);
            case BUY_ARM_EVENT -> new BuyArmEvent(player);
            case BUY_STUFF_CARRIER_EVENT -> new BuyStuffCarrierEvent(player);
            case POLICE_COMBAT_EVENT -> new PoliceCombatEvent(player);
            case GAME_OVER_EVENT -> new GameOverEvent(player);
            case RECLAIM_DEBT_EVENT -> new ReclaimDebtEvent(player);
            case MONEY_ROBBERY_EVENT -> new MoneyRobberyEvent(player);
            case STUFF_ROBBERY_EVENT -> new StuffRobberyEvent(player);
            case DAILY_PRICE_UPDATE_EVENT -> new UpdatePricesDailyEvent(player);
        };
    }
}
