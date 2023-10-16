package com.sorianotapia.screens;

import com.sorianotapia.controller.Controller;
import com.sorianotapia.controller.GameSettings;
import com.sorianotapia.auxiliaries.TextContainer;
import com.sorianotapia.player.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

public class TravelScreen extends AbstractScreen {

    public TravelScreen(ScreenName name) {
        super(name);
    }

    @Override
    public String render(Player player) {
        int[] ticketPrices = PlaceContainer.returnTicketPrices(player.getLocation());
        StringBuilder fareTable = new StringBuilder();

        int halfTable = GameSettings.NUMBER_OF_PLACES/2;
        for (int i = 0; i < halfTable; i++) {
            String priceRow = String.format(TextContainer.getGeneralTexts("travelPriceRow"),
                    i+1, PlaceContainer.getPlaceName(i), ticketPrices[i],
                    i+halfTable+1, PlaceContainer.getPlaceName(i+halfTable), ticketPrices[i+halfTable]);
            fareTable.append(priceRow);
        }
        return String.format(prompt, fareTable);
    }

    @Override
    public void handleUserInput(Player player) {

        int destinationIndex = Integer.parseInt(Controller.inputBuffer.get(0))-1;
        Place destination = PlaceContainer.getPlaceByIndex(destinationIndex);
        int price = PlaceContainer.returnTicketPrices(player.getLocation())[destinationIndex];
        switch (player.travel(destination, price)) {
            case SUCCESS -> {
                Controller.setDisplayInformationBuffer(new Object[]{destination.getName()});
                setNextScreen(ScreenFactory.ofName(ScreenName.TRAVEL_OK));
            }
            case INSUFFICIENT_MONEY -> {
                Controller.setDisplayInformationBuffer(new Object[]{destination.getName()});
                setNextScreen(ScreenFactory.ofName(ScreenName.TRAVEL_TOO_EXPENSIVE));
            }
            case SAME_ORIGIN_AND_DESTINATION -> {
                setNextScreen(ScreenFactory.ofName(ScreenName.TRAVEL_NOT_OK));
            }

        }
    }
}
