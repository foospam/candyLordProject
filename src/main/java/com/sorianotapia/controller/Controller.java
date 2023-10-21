package com.sorianotapia.controller;

import com.sorianotapia.auxiliaries.TextContainer;
import com.sorianotapia.events.Event;
import com.sorianotapia.events.EventMessage;
import com.sorianotapia.events.EventFactory;
import com.sorianotapia.events.EventQueue;
import com.sorianotapia.player.LoanSharkDebt;
import com.sorianotapia.player.Player;
import com.sorianotapia.screens.AbstractScreen;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.io.IOException;
import java.util.*;


public class Controller {

    private AbstractScreen screen;
    public static ArrayList<String> inputBuffer;
    private Scanner scanner;
    private static Player player;
    private ScreenFactory screenFactory;
    public static GameDate date;
    private GameInfo gameInfo;
    private static EventQueue eventMessageQueue;
    private static Object[] displayInformationBuffer;

    public static void setGameDate(GameDate gameDate){
        date = gameDate;
    }

    public Controller(Player newPlayer, GameDate gameDate) {
        inputBuffer = new ArrayList<>();
        scanner = new Scanner(System.in);
        player = newPlayer;
        //player = new Player();
        screenFactory = new ScreenFactory();
        screen = screenFactory.ofName(ScreenName.WELCOME_SCREEN);
        date = gameDate;
        gameInfo = new GameInfo(player, gameDate);
        eventMessageQueue = new EventQueue();
    }

    public static void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    public void run() {
        while (true) {
            render();

            if (screen.getName() == ScreenName.EVENT_LOOP) {
                handleEvents();
            }
            getUserInput();
            handleUserInput();
            if (screen.getName() == ScreenName.GOOD_BYE) {
                break;
            }

            update();
        }
    }


    private void getUserInput() {
        String validInput = screen.getValidInput();
        if (null != validInput) {
            String inputString = "FALSE_INPUT";
            boolean first = true;
            while (!inputString.matches(validInput)) {
                if (first) first = false;
                else System.out.printf(screen.getBadInputPrompt(), inputString);

                inputString = scanner.nextLine();
            }
            inputBuffer.add(inputString);
        }
    }

    private void handleUserInput() {
        screen.handleUserInput(player);
    }

    private void handleEvents() {
        if (screen.getName() == ScreenName.EVENT_LOOP) {

            boolean localEvent = false;

            if (eventMessageQueue.isEmpty()) {
                screen = ScreenFactory.ofName(ScreenName.MAIN_SELECTION);
                render();
            }

            while (!eventMessageQueue.isEmpty()) {
                System.out.println(eventMessageQueue.toString());

                Event event = eventMessageQueue.poll();
                event.run(this);
                localEvent = event.isLocalEvent();
                if (localEvent) render();
                if (localEvent) break;

            }
        }
    }


    private void render() {
        if (screen.getHeading()) {
            gameInfo.updateGameInfo(player, date);
            System.out.println(gameInfo.printUserStats());
            System.out.println(gameInfo.printUserHolster());
            System.out.println(gameInfo.printStuff());
        }
        String prompt = screen.render(player);
        if (prompt != null && prompt != "null") System.out.println(prompt);
    }

    public void setScreen(AbstractScreen screen) {
        this.screen = screen;
    }

    private void update() {
        if (!screen.passInput()) inputBuffer.clear();

        int advanceDays = screen.getAdvanceDay();
        if (advanceDays > 0) {
            date.updateDate(advanceDays);
            EventFactory.pushDailyPriceUpdateEvent(player);
            EventFactory.pushRandomPlaceEvents(player);
            EventFactory.pushRandomUserEvents(player);
        }

        if (screen.getTransitionDelay()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        setScreen(screen.getNextScreen());
    }

    public static void pushEventMessage(EventMessage eventMessage) {
        eventMessageQueue.add(eventMessage);
    }

    public static Object[] getDisplayInformationBuffer() {
        Object[] buffer = displayInformationBuffer;
        displayInformationBuffer = null;
        return buffer;
    }

    public static void setDisplayInformationBuffer(Object[] displayInformation) {
        displayInformationBuffer = displayInformation;
    }

    public static void resetDisplayInformationBuffer() {
        displayInformationBuffer = null;
    }
}
