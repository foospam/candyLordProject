package com.sorianotapia;

import com.sorianotapia.fromVersion1.Player;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Player player = new Player();
        GameDate gameDate = new GameDate();
        Controller controller = new Controller(player, gameDate);

    }
}