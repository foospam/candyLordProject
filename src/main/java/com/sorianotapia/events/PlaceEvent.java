package com.sorianotapia.events;

import com.sorianotapia.Controller;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.screens.ScreenFactory;

import java.util.ArrayList;

public abstract class PlaceEvent implements Event {

        public Place place;
        public Player player;

        public PlaceEvent(Player player) {
            this.player = player;
        }

        public abstract void run(Controller controller);

        public boolean isLocalEvent(){
            return place == player.getLocation();
        }

        public void setPlace(Place place){
            this.place = place;
        }

        public void setPlayer(Player player) {this.player = player;}


    }
