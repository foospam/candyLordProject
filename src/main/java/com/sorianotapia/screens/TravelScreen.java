package com.sorianotapia.screens;

import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

import java.util.ArrayList;

public class TravelScreen extends AbstractScreen {

    public TravelScreen(ScreenName name) {
        super(name);
    }

    @Override
    public String render(ArrayList<String> inputBuffer, Player player) {
        String worldMap = PlaceContainer.getMap();
        int[] ticketPrices = PlaceContainer.returnTicketPrices(player.getLocation());
        StringBuilder fareTable = new StringBuilder();

        for (int i = 0; i < ticketPrices.length; i++) {
            String priceRow = String.format("%d. %s (%s $)%n", i+1, PlaceContainer.getPlaceName(i), ticketPrices[i]);
            fareTable.append(priceRow);
        }
        return String.format(prompt, worldMap+"\n", fareTable);
    }

    @Override
    public void handleUserInput(ArrayList<String> stringArrayList, Player player, ScreenFactory screenFactory) {

        int destinationIndex = Integer.parseInt(stringArrayList.get(0))-1;
        Place destination = PlaceContainer.getPlaceByIndex(destinationIndex);
        int price = PlaceContainer.returnTicketPrices(player.getLocation())[destinationIndex];
        switch (player.travel(destination, price)) {
            case 0 -> {
                stringArrayList.clear();
                stringArrayList.add(destination.getName());
                setNextScreen(screenFactory.ofName(ScreenName.TRAVEL_OK));
            }
            case -1 -> {
                stringArrayList.clear();
                stringArrayList.add(destination.getName());
                setNextScreen(screenFactory.ofName(ScreenName.TRAVEL_TOO_EXPENSIVE));
            }
            case 2 -> {
                setNextScreen(screenFactory.ofName(ScreenName.TRAVEL_NOT_OK));
            }

        }
    }
}
