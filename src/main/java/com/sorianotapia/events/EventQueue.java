package com.sorianotapia.events;

import java.util.LinkedList;

public class EventQueue {
    LinkedList<PlaceEvent> nonLocalPlaceEvents;
    LinkedList<UserEvent> randomUserEvents;
    LinkedList<PlaceEvent> localPlaceEvents;
    LinkedList<UserEvent> triggeredUserEvents;
    GameOverEvent gameOverEvent;

    public EventQueue() {
        nonLocalPlaceEvents = new LinkedList<>();
        randomUserEvents = new LinkedList<>();
        localPlaceEvents = new LinkedList<>();
        triggeredUserEvents = new LinkedList<>();
    }

    public Event poll() {
        if (gameOverEvent != null) {
            GameOverEvent result = this.gameOverEvent;
            this.gameOverEvent = null;
            return result;
        } else if (!nonLocalPlaceEvents.isEmpty()) {
            return nonLocalPlaceEvents.poll();
        } else if (!randomUserEvents.isEmpty()) {
            return randomUserEvents.poll();
        } else if (!localPlaceEvents.isEmpty()) {
            return localPlaceEvents.poll();
        } else if (!triggeredUserEvents.isEmpty()) {
            return triggeredUserEvents.poll();
        } else return null;
    }

    public boolean isEmpty() {
        return (gameOverEvent == null &&
                nonLocalPlaceEvents.isEmpty() &&
                randomUserEvents.isEmpty() &&
                localPlaceEvents.isEmpty() &&
                triggeredUserEvents.isEmpty());
    }

    public void add(EventMessage eventMessage) {

        Event event = eventMessage.getEvent();

        if (event instanceof PlaceEvent) {
            if (event.isLocalEvent() && !localPlaceEvents.contains(event)) {
                localPlaceEvents.add((PlaceEvent) event);
            } else {
                nonLocalPlaceEvents.add((PlaceEvent) event);
            }

        } else if (event instanceof UserEvent) {
            if (event instanceof GameOverEvent) {
                gameOverEvent = (GameOverEvent) event;
            } else if (event instanceof ReclaimDebtEvent) {
                triggeredUserEvents.add((ReclaimDebtEvent) event);
            } else {
                randomUserEvents.add((UserEvent) event);
            }
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(gameOverEvent != null ? gameOverEvent.toString() : null);
        result.append(nonLocalPlaceEvents.toString());
        result.append(randomUserEvents.toString());
        result.append(localPlaceEvents.toString());
        result.append(triggeredUserEvents.toString());
        return result.toString();
    }
}
