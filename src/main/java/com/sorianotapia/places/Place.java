package com.sorianotapia.places;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sorianotapia.GameSettings;
import com.sorianotapia.stuff.Stuff;
import com.sorianotapia.stuff.StuffContainer;
import org.w3c.dom.Node;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class Place {

    private String name;
    private Point coordinates;
    private StuffContainer stuffContainer;


    public Place(String name, Point coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.stuffContainer = new StuffContainer();
    }

    public Place(String name, int x, int y){
        new Place(name, new Point(x,y));
    }

    public Place(){
    }

    public double distanceTo(Place other) {
        return coordinates.distance(other.getCoordinates());
    }


    public int getStuffPrice(String stuffName) {
        return stuffContainer.getPrice(stuffName);
    }

    public int getStuffPrice(int index){
        return stuffContainer.getPrice(index);
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

    public void updateStuffPrices(){
        stuffContainer.updateStuffPrices(GameSettings.RAMDOM_PRICE_VARIATION_PERCENTAGE);
    }

    public Point getCoordinates(){
        return coordinates;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Point coordinates){
        this.coordinates = coordinates;
    }
}

