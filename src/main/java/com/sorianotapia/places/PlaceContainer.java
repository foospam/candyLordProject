package com.sorianotapia.places;

import java.awt.*;
import java.util.ArrayList;

public class PlaceContainer {
    private ArrayList<Place> places;

    private final int NUMBER_OF_PLACES;
    private final int PRICE_PER_KM;
    private final int WORLD_SIZE;
    private final int MIN_DISTANCE;

    public static void main(String[] args) {
        PlaceContainer container = new PlaceContainer();
        ArrayList<Point> points = container.getPoints();
        System.out.println(points);
        container.getMap(points);
    }

    public PlaceContainer() {
        PRICE_PER_KM = 5;
        WORLD_SIZE = 20;
        MIN_DISTANCE = 5;
        NUMBER_OF_PLACES = 8;
        places = new ArrayList<>();
        ArrayList<Point> points = getPoints();

        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            String placeName = NameContainer.getRandomPlaceName();
            Point placeCoordinates = points.get(i);
            places.add(new Place(placeName, placeCoordinates));
        }
    }

    public double[] returnTicketPrices(PlaceName name) {
        double[] ticketPrices = new double[values.size()];
        Place origin = getPlaceByName(name);
        for (int i = 0; i < values.size(); i++) {
            ticketPrices[i] = origin.distanceTo(values.get(i));
        }
        return ticketPrices;
    }

    public Place getPlaceByName(PlaceName name) {
        for (Place place : values
        ) {
            if (place.getInternalName() == name) {
                return place;
            }
        }
        return null;
    }

//    private addPlace(Place place) {
//
//    }

    private ArrayList<Point> getPoints() {
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

    private Point getRandomPoint() {
        return new Point((int) (Math.random() * WORLD_SIZE), (int) (Math.random() * WORLD_SIZE));
    }

    public String getMap(ArrayList<Point> points) {
        StringBuilder map = new StringBuilder("x".repeat(WORLD_SIZE * WORLD_SIZE));

        for (Point point :
                points) {
            int pointLocation = point.x * WORLD_SIZE + point.y;
            map.replace(pointLocation, pointLocation + 1, String.valueOf(points.indexOf(point)+1));


        }
        for (int i = 1; i < WORLD_SIZE; i++)
            map.insert(i * WORLD_SIZE + i - 1, "\n");
        System.out.println(map);
        System.out.println(map.toString().replaceAll("x", "   ").replaceAll("(\\d)", " $1 "));
        return map.toString();
    }

}
