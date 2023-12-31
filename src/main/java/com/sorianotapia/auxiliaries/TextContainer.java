package com.sorianotapia.auxiliaries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sorianotapia.goods.Arm;
import com.sorianotapia.goods.StuffCarrier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextContainer {

    private static ArrayList<String> placeNames = new ArrayList<>();

    private static ArrayList<String> stuffNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        TextContainer.setFile("texts.json");
        System.out.println(root.get("screen_texts").get("default_values").get("prompt").isNull());
    }

    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectNode root;


    public static String getScreenText(String screenName, String stringText) {
        try {
            return root.get("screen_texts").get(screenName).get(stringText).asText();
        } catch (NullPointerException E) {
            if (root.get("screen_texts").get("default_values").get(stringText).isNull())
                return null;
            else return root.get("screen_texts").get("default_values").get(stringText).asText();
        }
    }

    ;

    public static boolean getScreenBool(String screenName, String stringText) {
        try {
            return root.get("screen_texts").get(screenName).get(stringText).asBoolean();
        } catch (NullPointerException E) {
            return root.get("screen_texts").get("default_values").get(stringText).asBoolean();
        }
    }

    public static int getScreenInt(String screenName, String stringText) {
        try {
            return root.get("screen_texts").get(screenName).get(stringText).asInt();
        } catch (NullPointerException E) {
            return root.get("screen_texts").get("default_values").get(stringText).asInt();
        }
    }


    private static void readAllPlaceNames() {
        try {
            ArrayNode nameSource = (ArrayNode) root.get("placeNames");
            nameSource.forEach(s -> placeNames.add(s.toString().replace("\"", "")));
        } catch (NullPointerException E) {
            throw new NullPointerException("The name file does not contain any 'placeNames' element.");
        }
    }

    private static void readAllStuffNames() {
        try {
            ArrayNode nameSource = (ArrayNode) root.get("stuffNames");
            nameSource.forEach(s -> stuffNames.add(s.toString().replace("\"", "")));
        } catch (NullPointerException E) {
            throw new NullPointerException("The name file does not contain any 'stuffNames' element.");
        }
    }

    public static void setFile(String fileName) throws IOException {
        root = (ObjectNode) mapper.readTree(new File(fileName));
        readAllPlaceNames();
        readAllStuffNames();
    }

    public static String getRandomPlaceName() {
        if (placeNames.size() > 0) {
            int index = (int) (Math.random() * placeNames.size());
            String name = placeNames.get(index);
            placeNames.remove(index);
            return name;
        }
        return null;
    }

    public static ArrayList<String> getPlaceNames() {
        return placeNames;
    }

    public static ArrayList<String> getStuffNames() {
        return stuffNames;
    }

    public static ArrayList<StuffCarrier> getAllStuffCarriers() {
        ArrayList<StuffCarrier> stuffCarriers = new ArrayList<>();

        try {
            root.get("stuffCarriers").forEach(s -> {
                String name = s.get("name").asText();
                int hold = s.get("hold").asInt();
                int price = s.get("price").asInt();
                StuffCarrier carrier = new StuffCarrier(name, hold, price);
                stuffCarriers.add(carrier);
            });
        } catch (NullPointerException E) {
            throw new NullPointerException("The name file does not contain any carrier elements.");
        }
        return stuffCarriers;
    }

    public static ArrayList<Arm> readAllArms() {

        ArrayList<Arm> arms = new ArrayList<>();

        try {
            root.get("arms").forEach(s -> {
                String name = s.get("name").asText();
                int harm = s.get("harm").asInt();
                int accuracy = s.get("accuracy").asInt();
                int price = s.get("price").asInt();
                boolean isDefault = s.has("default") ? true : false;
                Arm arm = new Arm(name, harm, accuracy, price, isDefault);
                arms.add(arm);
            });
        } catch (NullPointerException E) {
            throw new NullPointerException("The name file does not contain any arm elements.");
        }
        return arms;
    }

    public static String getBattlePrompts(String string) {

        String prompt = "";

        try {
            prompt = root.get("battlePrompts").get(string).asText();
        } catch (NullPointerException E) {
            throw new NullPointerException("The text could not be found.");
        }

        return prompt;
    }

    public static String getGeneralTexts(String string) {

        String prompt = "";

        try {
            prompt = root.get("generalTexts").get(string).asText();
        } catch (NullPointerException E) {
            throw new NullPointerException("The text could not be found.");
        }

        return prompt;
    }

}