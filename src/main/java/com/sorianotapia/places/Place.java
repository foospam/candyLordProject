package com.sorianotapia.places;

import com.sorianotapia.fromVersion1.Drugs;
import com.sorianotapia.stuff.Stuff;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Place {
    private String name;
    private Point coordinates;
    private String printName;
    private ArrayList<Stuff> stuffList;
    private HashMap<String, Integer> stuffQuantityList;
    private HashMap<String, Producer> stuffProducerList;

    public double distanceTo(Place other){
        return coordinates.distance(other.getCoordinates());
    }

    public Point getCoordinates(){
        return coordinates;
    }

    public PlaceName getInternalName(){
        return internalName;
    }

    public Place(String name, Point coordinates){
        this.name = name;
        this.coordinates = coordinates;
    }
}
