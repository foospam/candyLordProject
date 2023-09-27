package com.sorianotapia;

import com.sorianotapia.fromVersion1.Drugs;
import com.sorianotapia.fromVersion1.LoanSharkDebt;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.screens.ScreenAbstract;
import com.sorianotapia.screens.ScreenFactory;
import com.sorianotapia.screens.ScreenName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) throws IOException {
        GameDate gameDate = new GameDate();
        Player player = new Player();
        LoanSharkDebt debt = new LoanSharkDebt();
        player.setDebt(debt);
        gameDate.subscribe(debt);


        TextContainer.setFile("texts.json");
        Controller controller = new Controller(player, gameDate);
        //controller.player.setHealth(50);
        controller.run();
    }

    private ScreenAbstract screen;
    private ArrayList<String> inputBuffer;
    private Scanner scanner;
    private Player player;
    private ScreenFactory screenFactory;
    private GameDate date;

    public Controller(Player player, GameDate gameDate){
        inputBuffer = new ArrayList<>();
        scanner = new Scanner(System.in);
        this.player = player;
        //player = new Player();
        screenFactory = new ScreenFactory();
        screen = screenFactory.ofName(ScreenName.MAIN_SELECTION);
        date = gameDate;
    }

    public void run(){
        while (true) {
            render();
            getEvent();
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
        screen.handleUserInput(inputBuffer, player, screenFactory);
    }

    private void getEvent() {
    }

    private void render() {
        if (null != screen.getHeading())
            System.out.printf(screen.getHeading().getTemplate()+"%n", getFullStats());
        String prompt = screen.render(inputBuffer, player);
        System.out.println(prompt);
    }

    private void setScreen(ScreenAbstract screen){
        this.screen = screen;
    }

    public Object[] getFullStats(){
        Object[] objects = new Object[28];
        objects[0] = player.getDrugsOnHand(Drugs.COCAINE);
        objects[1] = player.getDrugsOnHand(Drugs.CRACK);
        objects[2] = player.getDrugsOnHand(Drugs.HEROIN);
        objects[3] = player.getDrugsOnHand(Drugs.ACID);
        objects[4] = player.getDrugsOnHand(Drugs.CRYSTAL);
        objects[5] = player.getDrugsOnHand(Drugs.GRASS);
        objects[6] = player.getDrugsOnHand(Drugs.SPEED);
        objects[7] = player.getDrugsOnHand(Drugs.LUDES);
        objects[8] = Drugs.COCAINE.getPrice();
        objects[9] = Drugs.CRACK.getPrice();
        objects[10] = Drugs.HEROIN.getPrice();
        objects[11] = Drugs.ACID.getPrice();
        objects[12] = Drugs.CRYSTAL.getPrice();
        objects[13] = Drugs.GRASS.getPrice();
        objects[14] = Drugs.SPEED.getPrice();
        objects[15] = Drugs.LUDES.getPrice();
        objects[16] = date.getStringDate();
        objects[17] = player.getLocation().printableName();
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
}
