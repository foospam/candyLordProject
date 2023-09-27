package com.sorianotapia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class TextContainer {

    public static void main(String[] args) throws IOException {
        TextContainer.setFile("texts.json");
        System.out.println(root.get("default_values").get("prompt").isNull());
    }

    private static ObjectMapper mapper = mapper = new ObjectMapper();
    private static ObjectNode root;


    public static String getScreenText(String screenName, String stringText){
        try {
            return root.get(screenName).get(stringText).asText();
        }
        catch (NullPointerException E) {
            if (root.get("default_values").get(stringText).isNull())
                return null;
            else return root.get("default_values").get(stringText).asText();
        }
    };

    public static boolean getScreenBool(String screenName, String stringText){
        try {
            return root.get(screenName).get(stringText).asBoolean();
        }
        catch (NullPointerException E) {
            return root.get("default_values").get(stringText).asBoolean();
        }
    }

    public static int getScreenInt(String screenName, String stringText){
        try {
            return root.get(screenName).get(stringText).asInt();
        }
        catch (NullPointerException E) {
            return root.get("default_values").get(stringText).asInt();
        }
    }

    public static void setFile(String fileName) throws IOException {
        root = (ObjectNode) mapper.readTree(new File(fileName));
    }
}