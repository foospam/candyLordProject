package com.sorianotapia.places;

import com.sorianotapia.GameSettings;
import com.sorianotapia.TextContainer;

import java.awt.*;
import java.util.ArrayList;

public class PlaceContainer {
    private static ArrayList<Place> places;


    static {
        places = new ArrayList<>();
        ArrayList<Point> points = getPoints();

        for (int i = 0; i < GameSettings.NUMBER_OF_PLACES; i++) {
            String placeName = TextContainer.getRandomPlaceName();
            Point placeCoordinates = points.get(i);
            Place place = new Place(placeName, placeCoordinates);
            places.add(place);
        }
    }


    public static int[] returnTicketPrices(Place origin) {
        int[] ticketPrices = new int[places.size()];
        for (int i = 0; i < places.size(); i++) {
            ticketPrices[i] = (int) (origin.distanceTo(places.get(i)) * GameSettings.PRICE_PER_KM);
        }
        return ticketPrices;
    }

    public static int[] returnTicketPrices(int placeIndex) {
        Place origin = places.get(placeIndex);
        return returnTicketPrices(origin);
    }

    public static int[] returnTicketPrices(String placeName) {
        Place origin = getPlaceByName(placeName);
        return returnTicketPrices(origin);
    }

    public static Place getPlaceByName(String name) {
        for (Place place : places
        ) {
            if (place.getName().equals(name)) {
                return place;
            }
        }
        return null;
    }

//    private addPlace(Place place) {
//
//    }

    private static ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(getRandomPoint());

        externalloop:
        while (points.size() < GameSettings.NUMBER_OF_PLACES) {
            Point point = getRandomPoint();
            for (Point point2 : points) {
                if (point.distance(point2) < GameSettings.MIN_DISTANCE) continue externalloop;
            }
            ;
            points.add(point);
        }

        return points;
    }

    private static Point getRandomPoint() {
        return new Point((int) (Math.random() * GameSettings.WORLD_SIZE), (int) (Math.random() * GameSettings.WORLD_SIZE));
    }

    public static String getMap(){
        ArrayList<Point> points = new ArrayList<>();
        places.forEach(p -> points.add(p.getCoordinates()));
        return getMap(points);
    }

    public static String getMap(ArrayList<Point> points) {
        StringBuilder map = new StringBuilder("x".repeat(GameSettings.WORLD_SIZE * GameSettings.WORLD_SIZE));

        for (Point point :
                points) {
            int pointLocation = point.x * GameSettings.WORLD_SIZE + point.y;
            map.replace(pointLocation, pointLocation + 1, String.valueOf(points.indexOf(point)+1));


        }
        for (int i = 1; i < GameSettings.WORLD_SIZE; i++)
            //╔═╗║
            map.insert(i * GameSettings.WORLD_SIZE + (3*(i-1)), "║\n║");

        map.insert(0, "╔"+"═".repeat(GameSettings.WORLD_SIZE*3)+"╗\n║");
        map.insert(map.length()-2, "║\n╚"+"═".repeat(GameSettings.WORLD_SIZE*3)+"╝\n");

        String worldMap = map.toString().replaceAll("x", "   ").replaceAll("(\\d)", " $1 ");
        return worldMap;
    }

    public static Place getRandomPlace(){
        return places.get((int) (Math.random() * places.size()));
    }

    public static String getPlaceName(int index){
        return places.get(index).getName();
    }

    public static Place getPlaceByIndex(int index){
        return places.get(index);
    }

    public static int size(){
        return places.size();
    }

    public static void randomUpdateStuffPrices(){
        for (Place place : places){
            place.updateStuffPrices();
        }
    }

    public static void setPlaces(ArrayList<Place> placeArray){
        places = placeArray;
    }
}
