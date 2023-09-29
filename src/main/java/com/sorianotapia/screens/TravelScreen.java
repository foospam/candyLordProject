package com.sorianotapia.screens;

import com.sorianotapia.Controller;
import com.sorianotapia.MethodAnswers;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

import java.util.ArrayList;

public class TravelScreen extends AbstractScreen {

    public TravelScreen(ScreenName name) {
        super(name);
    }

    @Override
    public String render(Player player) {
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
    public void handleUserInput(Player player) {

        int destinationIndex = Integer.parseInt(Controller.inputBuffer.get(0))-1;
        Place destination = PlaceContainer.getPlaceByIndex(destinationIndex);
        int price = PlaceContainer.returnTicketPrices(player.getLocation())[destinationIndex];
        switch (player.travel(destination, price)) {
            case SUCCESS -> {
                Controller.inputBuffer.clear();
                Controller.inputBuffer.add(destination.getName());
                setNextScreen(ScreenFactory.ofName(ScreenName.TRAVEL_OK));
            }
            case INSUFFICIENT_MONEY -> {
                Controller.inputBuffer.clear();
                Controller.inputBuffer.add(destination.getName());
                setNextScreen(ScreenFactory.ofName(ScreenName.TRAVEL_TOO_EXPENSIVE));
            }
            case SAME_ORIGIN_AND_DESTINATION -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.TRAVEL_NOT_OK));
            }

        }
    }
}
