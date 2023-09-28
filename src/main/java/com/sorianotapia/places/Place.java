package com.sorianotapia.places;

import com.sorianotapia.stuff.Stuff;
import com.sorianotapia.stuff.StuffContainer;

import java.awt.Point;

public class Place {
    private String name;
    private Point coordinates;
    private StuffContainer stuffContainer;
    //private HashMap<String, Integer> stuffQuantityList;
    //private HashMap<String, Producer> stuffProducerList;


    public double distanceTo(Place other) {
        return coordinates.distance(other.getCoordinates());
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public Place(String name, Point coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.stuffContainer = new StuffContainer();
    }

    public int getStuffPrice(String stuffName) {
        return stuffContainer.getPrice(stuffName);
    }

    public String getName() {
        return name;
    }

    public String getStuffName(int index){
        return stuffContainer.getStuffName(index);
    }

    public Stuff getStuff(int index){
        return stuffContainer.getStuff(index);
    }

    public Stuff getRandomStuff(){
        int randomIndex = (int) (Math.random() * stuffContainer.getSize());
        return getStuff(randomIndex);
    }

}
