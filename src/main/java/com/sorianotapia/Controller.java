package com.sorianotapia;
import com.sorianotapia.events.Event;
import com.sorianotapia.events.EventMessage;
import com.sorianotapia.events.EventFactory;
import com.sorianotapia.fromVersion1.LoanSharkDebt;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.NameContainer;
import com.sorianotapia.screens.AbstractScreen;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;
import java.io.IOException;
import java.util.*;


public class Controller {

    public static void main(String[] args) throws IOException {
        TextContainer.setFile("texts.json");
        NameContainer.setFile("placeNames.json");
        GameDate gameDate = new GameDate();
        Player player = new Player();
        LoanSharkDebt debt = new LoanSharkDebt();
        player.setDebt(debt);
        gameDate.subscribe(debt);



        Controller controller = new Controller(player, gameDate);
        //controller.player.setHealth(50);
        controller.run();
    }

    private AbstractScreen screen;
    public static ArrayList<String> inputBuffer;
    private Scanner scanner;
    private Player player;
    private ScreenFactory screenFactory;
    private GameDate date;
    private static LinkedList<EventMessage> eventMessageQueue;
    private EventFactory eventFactory;

    public Controller(Player player, GameDate gameDate){
        inputBuffer = new ArrayList<>();
        scanner = new Scanner(System.in);
        this.player = player;
        //player = new Player();
        screenFactory = new ScreenFactory();
        screen = screenFactory.ofName(ScreenName.MAIN_SELECTION);
        date = gameDate;
        eventFactory = new EventFactory(player.getLocation(), player);
        eventMessageQueue = new LinkedList<>();
    }

    public void run(){
        while (true) {
            render();
            handleEvents();
            getUserInput();
            handleUserInput();
            update();
        }
    }


    private void getUserInput() {
        String validInput = screen.getValidInput();
        if (null != validInput) {
            String inputString = "";
            boolean first = true;
            while (!inputString.matches(validInput)){
                if (first) first = false;
                else System.out.printf(screen.getBadInputPrompt(), inputString);

                inputString = scanner.next();
            }
            inputBuffer.add(inputString);
        }
    }

    private void handleUserInput() {
        screen.handleUserInput(player);
    }

    private void handleEvents() {
        if (screen.getName() == ScreenName.MAIN_SELECTION) {
            eventFactory.pushRandomEvents(player);

            boolean localEvent = false;

            while (!eventMessageQueue.isEmpty()) {

                Event event = eventMessageQueue.poll().getEvent();
                event.run(this, screenFactory, inputBuffer);
                localEvent = event.isLocalEvent();

                if (localEvent) break;
            }
            if (localEvent) render();
        }
    }


    private void render() {
        if (null != screen.getHeading())
            System.out.printf(screen.getHeading().getTemplate()+"%n", getFullStats());
        String prompt = screen.render(player);
        System.out.println(prompt);
    }

    public void setScreen(AbstractScreen screen){
        this.screen = screen;
    }

    public Object[] getFullStats(){
        Object[] objects = new Object[28];
        objects[0] = player.getStuffOnHand(0);
        objects[1] = player.getStuffOnHand(1);
        objects[2] = player.getStuffOnHand(2);
        objects[3] = player.getStuffOnHand(3);
        objects[4] = player.getStuffOnHand(4);
        objects[5] = player.getStuffOnHand(5);
        objects[6] = player.getStuffOnHand(6);
        objects[7] = player.getStuffOnHand(7);
        objects[8] = player.getLocation().getStuffPrice(player.translateStuffIndexToName(0));
        objects[9] = player.getLocation().getStuffPrice(player.translateStuffIndexToName(1));
        objects[10] = player.getLocation().getStuffPrice(player.translateStuffIndexToName(2));
        objects[11] = player.getLocation().getStuffPrice(player.translateStuffIndexToName(3));
        objects[12] = player.getLocation().getStuffPrice(player.translateStuffIndexToName(4));
        objects[13] = player.getLocation().getStuffPrice(player.translateStuffIndexToName(5));
        objects[14] = player.getLocation().getStuffPrice(player.translateStuffIndexToName(6));
        objects[15] = player.getLocation().getStuffPrice(player.translateStuffIndexToName(7));
        objects[16] = date.getStringDate();
        objects[17] = player.getLocation().getName();
        objects[18] = player.getHold();
        objects[19] = player.getMaxHold();
        objects[20] = player.getCash();
        objects[21] = player.getDeposits();
        objects[22] = player.getDebtValue();
        objects[23] = player.getDebtValue() > 0 ?  String.format("(%2d)", player.getDebtDays()) : "";
        objects[24] = player.getNumberOfGuns();
        objects[25] = player.getGunType();
        objects[26] = player.getReputation();
        objects[27] = player.getHealth();

        return objects;
    }

    private void update() {
        if (!screen.passInput()) inputBuffer.clear();

        date.updateDate(screen.getAdvanceDay());

        if (screen.getTransitionDelay()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        setScreen(screen.getNextScreen());
    }

    public static void pushEventMessage(EventMessage eventMessage){
        eventMessageQueue.add(eventMessage);
    }
}
