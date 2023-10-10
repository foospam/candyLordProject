package com.sorianotapia.places;

import com.sorianotapia.TextContainer;

import java.awt.*;
import java.util.ArrayList;

public class PlaceContainer {
    private static ArrayList<Place> places;

    private static int NUMBER_OF_PLACES;
    private static int PRICE_PER_KM;
    private static int WORLD_SIZE;
    private static int MIN_DISTANCE;

    static {
        WORLD_SIZE = 20;
        PRICE_PER_KM = 500/WORLD_SIZE;
        MIN_DISTANCE = 5;
        NUMBER_OF_PLACES = 8;
        places = new ArrayList<>();
        ArrayList<Point> points = getPoints();

        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            String placeName = TextContainer.getRandomPlaceName();
            Point placeCoordinates = points.get(i);
            Place place = new Place(placeName, placeCoordinates);
            places.add(place);
        }
    }


    public static int[] returnTicketPrices(Place origin) {
        int[] ticketPrices = new int[places.size()];
        for (int i = 0; i < places.size(); i++) {
            ticketPrices[i] = (int) (origin.distanceTo(places.get(i)) * PRICE_PER_KM);
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
        while (points.size() < NUMBER_OF_PLACES) {
            Point point = getRandomPoint();
            for (Point point2 : points) {
                if (point.distance(point2) < MIN_DISTANCE) continue externalloop;
            }
            ;
            points.add(point);
        }

        return points;
    }

    private static Point getRandomPoint() {
        return new Point((int) (Math.random() * WORLD_SIZE), (int) (Math.random() * WORLD_SIZE));
    }

    public static String getMap(){
        ArrayList<Point> points = new ArrayList<>();
        places.forEach(p -> points.add(p.getCoordinates()));
        return getMap(points);
    }

    public static String getMap(ArrayList<Point> points) {
        StringBuilder map = new StringBuilder("x".repeat(WORLD_SIZE * WORLD_SIZE));

        for (Point point :
                points) {
            int pointLocation = point.x * WORLD_SIZE + point.y;
            map.replace(pointLocation, pointLocation + 1, String.valueOf(points.indexOf(point)+1));


        }
        for (int i = 1; i < WORLD_SIZE; i++)
            //╔═╗║
            map.insert(i * WORLD_SIZE + (3*(i-1)), "║\n║");
        System.out.println(map.toString());
        map.insert(0, "╔"+"═".repeat(WORLD_SIZE*3)+"╗\n║");
        map.insert(map.length()-2, "║\n╚"+"═".repeat(WORLD_SIZE*3)+"╝\n");

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
}
