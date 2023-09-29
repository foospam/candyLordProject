package com.sorianotapia.events;
import com.sorianotapia.places.Place;

public class EventMessage {

    private Event event;
    private Place place;

    public Event getEvent(){
        event.setPlace(place);
        return event;
    };

    public EventMessage(Event event, Place place) {
        this.event = event;
        this.place = place;
    }

}
