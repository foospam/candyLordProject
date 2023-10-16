package com.sorianotapia;

import com.sorianotapia.auxiliaries.TextContainer;
import com.sorianotapia.controller.Controller;
import com.sorianotapia.controller.GameDate;
import com.sorianotapia.events.EventFactory;
import com.sorianotapia.player.LoanSharkDebt;
import com.sorianotapia.player.Player;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try {
            TextContainer.setFile("texts.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameDate gameDate = new GameDate();
        Player newPlayer = new Player();
        LoanSharkDebt debt = new LoanSharkDebt();
        newPlayer.setDebt(debt);
        gameDate.subscribe(debt);
        Player player = newPlayer;
        EventFactory.initializeEvents(player);
        Controller controller = new Controller(player, gameDate);

        controller.run();
    }
}