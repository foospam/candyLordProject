package com.sorianotapia.places;

import com.sorianotapia.controller.GameSettings;
import com.sorianotapia.goods.Stuff;
import com.sorianotapia.goods.StuffContainer;

import java.awt.Point;

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

